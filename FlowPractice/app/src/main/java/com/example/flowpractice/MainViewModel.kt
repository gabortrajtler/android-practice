package com.example.flowpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000)
            currentValue--
            emit(currentValue)
        }
    }//.shareIn(viewModelScope, started = SharingStarted.WhileSubscribed(), 0) // replay 0 -> collectFlow() starts from 8

    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {
            delay(1500) // when collecting cold flow it starts from 10, when from hot (sharedFlow) it starts from 8
            countDownFlow
                // transforming operators
                .filter { time ->
                    time % 2 == 0
                }
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println("TG $time")
                }
                // terminal operators - terminates the flow, take all emissions
                .collect { time ->
                    delay(1500)
                    println("TG Current time is: $time")
                }

            // if countDownFlow is hot (sharedFlow, stateFlow) collection will last "forever" - coroutine suspended
            // and never reaches this line

            // Terminal operators
            val countElements =
                countDownFlow
                    .count { time ->
                        time == 5 || time == 1
                    }
            println("TG Filtered emissions counted: $countElements")
        }

        viewModelScope.launch {
            val reduceResult =
                countDownFlow
                    .reduce { accumulator, value ->
                        accumulator + value     // accumulator holds the previous result of the calculation
                    }
            println("TG reduced result (sum): $reduceResult")

            val foldResult =
                countDownFlow
                    .fold(100) { accumulator, value ->
                        accumulator + value     // fold to set an initial value instead of 0
                    }
            println("TG reduced result (sum): $foldResult")
        }

        // Flattening flows - not really used
        // flatMapLatest - used like collectLatest
        viewModelScope.launch {
            val flow1 = flow {
                emit(1)
                delay(500L)
                emit(2)
            }

            flow1.flatMapConcat { value ->  // concat multiple flows like from DB & Network
                flow {
                    emit(value + 1)
                    delay(100L)
                    emit(value + 2)
                }
            }.collect {
                println("TG flatMapConcat result: $it")
            }
        }

        // buffer - conflate - collectLatest
        viewModelScope.launch {
            val dinnerFlow = flow {
                delay(500)
                emit("Appetizer")
                delay(1_000)
                emit("Main dish")
                delay(100)
                emit("Dessert")
            }

            dinnerFlow.onEach {
                println("TG DINNER: 1. $it is delivered")
            }
                .buffer()   // creates new coroutine for the lower flow, so onEach & collect can go simultaneously
                .collect {
                    println("TG DINNER: 2. Now eating $it")
                    delay(1_000)
                    println("TG DINNER: 3. Finished eating $it")
                }
        }
    }
}
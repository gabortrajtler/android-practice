package com.example.flowpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
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
    }.shareIn(viewModelScope, started = SharingStarted.WhileSubscribed(), 0) // replay 0 -> collectFlow() starts from 8

    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {
            delay(1500)
            countDownFlow.collect { time ->
                delay(1500)
                println("Current time is: $time")
            }
        }
    }
}
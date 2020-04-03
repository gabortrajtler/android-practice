package com.gabortrajtler.whattodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gabortrajtler.whattodo.database.WhatTodo
import com.gabortrajtler.whattodo.database.WhatTodoDatabase
import kotlinx.coroutines.launch

class WhatTodoViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: WhatTodoRepository
    // LiveData gives us updated words when they change.
    val allTodos: LiveData<List<WhatTodo>>
    lateinit var todo: WhatTodo

    init {
        // Gets reference to WhatTodoDatabaseDao from WhatTodoDatabase to construct
        // the correct WhatTodoRepository.
        val whatTodoDatabaseDao = WhatTodoDatabase.getDatabase(application, viewModelScope).whatTodoDatabaseDao()
        repository = WhatTodoRepository(whatTodoDatabaseDao)
        allTodos = repository.allTodos
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(whatTodo: WhatTodo) = viewModelScope.launch {
        repository.insert(whatTodo)
    }

    fun completeTodo(position: Int) {
        viewModelScope.launch {
            val id = repository.getLatestTodoId() - position
            repository.completeTodo(id)
        }
    }

}
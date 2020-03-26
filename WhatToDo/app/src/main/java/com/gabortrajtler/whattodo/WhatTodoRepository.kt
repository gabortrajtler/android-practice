package com.gabortrajtler.whattodo

import androidx.lifecycle.LiveData
import com.gabortrajtler.whattodo.database.WhatTodo
import com.gabortrajtler.whattodo.database.WhatTodoDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WhatTodoRepository (private val database: WhatTodoDatabaseDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allTodos: LiveData<List<WhatTodo>> = database.getAllTodos()

    suspend fun update(whatTodo: WhatTodo) {
        withContext(Dispatchers.IO) {
            database.update(whatTodo)
        }
    }

    suspend fun insert(whatTodo: WhatTodo) {
        withContext(Dispatchers.IO) {
            database.insert(whatTodo)
        }
    }
}
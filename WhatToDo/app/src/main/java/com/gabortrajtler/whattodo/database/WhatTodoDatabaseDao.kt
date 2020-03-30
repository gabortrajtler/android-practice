package com.gabortrajtler.whattodo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Defines methods for using the WhatTodo class with Room.
 */
@Dao
interface WhatTodoDatabaseDao {

    @Insert
    fun insert(whatTodo: WhatTodo)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param whatTodo new value to write
     */
    @Update
    fun update(whatTodo: WhatTodo)

    /**
     * Selects and returns all rows in the table,
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM whattodo_table ORDER BY todoId DESC")
    fun getAllTodos(): LiveData<List<WhatTodo>>

    /**
     * Selects and returns the whatTodo with given todoId.
     */
    @Query("SELECT * from whattodo_table WHERE todoId = :key")
    fun getTodoWithId(key: Long): WhatTodo?

    @Query("DELETE FROM whattodo_table")
    fun deleteAll()
}

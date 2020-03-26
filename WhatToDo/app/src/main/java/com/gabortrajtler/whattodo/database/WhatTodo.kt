package com.gabortrajtler.whattodo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "whattodo_table")
data class WhatTodo(
    @PrimaryKey(autoGenerate = true)
    var todoId: Long = 0L,

    @ColumnInfo(name = "todo_text")
    var todoText: String = "",

    @ColumnInfo(name = "is_completed")
    var isCompleted: Boolean = false
)
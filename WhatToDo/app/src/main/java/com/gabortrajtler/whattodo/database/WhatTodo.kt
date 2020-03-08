package com.gabortrajtler.whattodo.database

data class WhatTodo (
    var todoId: Long = 0L,
    var todoText: String = "",
    var isCompleted: Boolean = false
)
package com.gabortrajtler.whattodo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.gabortrajtler.whattodo.database.WhatTodo
import com.gabortrajtler.whattodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var whatTodoText: WhatTodo = WhatTodo(todoText = "enni")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.todoInView = whatTodoText

        binding.addTodoButton.setOnClickListener { addTodo(it) }
    }

    private fun addTodo(view: View) {
        binding.apply {
            whatTodoText.todoText += "\n\n" + whatTodoText.isCompleted + whatTodoText.todoId + addTodoEdit.text.toString()
            invalidateAll()
            addTodoEdit.text.clear()
        }

        // Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

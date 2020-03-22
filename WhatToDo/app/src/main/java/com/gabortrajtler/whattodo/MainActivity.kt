package com.gabortrajtler.whattodo

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabortrajtler.whattodo.database.WhatTodo

class MainActivity : AppCompatActivity() {
    lateinit var todoRecyclerView: RecyclerView
    lateinit var addTodoButton: Button
    lateinit var addTodoEdit: EditText
    var whatTodoTitles = mutableListOf("Shopping list", "inni", "Android tutorials")
    val whatTodo = WhatTodo(0, "Valami", false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoRecyclerView = findViewById(R.id.todo_recycleview)
        todoRecyclerView.layoutManager = LinearLayoutManager(this)
        todoRecyclerView.adapter = TodoSelectionRecyclerViewAdapter(whatTodoTitles)

        addTodoEdit = findViewById(R.id.add_todo_edit)

        addTodoButton = findViewById(R.id.add_todo_button)
        addTodoButton.setOnClickListener { addTodo(it) }
    }

    private fun addTodo(view: View?) {
        whatTodoTitles.add(addTodoEdit.text.toString())

        Toast.makeText(applicationContext, "Added: ${addTodoEdit.text}", Toast.LENGTH_SHORT).show()

        // Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}

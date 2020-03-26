package com.gabortrajtler.whattodo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabortrajtler.whattodo.database.WhatTodo

class TodoSelectionRecyclerViewAdapter(context: Context) : RecyclerView.Adapter<TodoSelectionViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var whatTodos = emptyList<WhatTodo>() // Cached copy of todos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoSelectionViewHolder {
        val view = inflater.inflate(R.layout.todo_selection_view_holder, parent, false)
        return TodoSelectionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return whatTodos.size
    }

    override fun onBindViewHolder(holder: TodoSelectionViewHolder, position: Int) {
        val currentTodo = whatTodos[position]
        holder.todoPosition.text = position.toString()
        holder.todoTitle.text = currentTodo.todoText
    }

    internal fun setTodos(whatTodos: List<WhatTodo>) {
        this.whatTodos = whatTodos

        // notify observers
        notifyDataSetChanged()
    }

}

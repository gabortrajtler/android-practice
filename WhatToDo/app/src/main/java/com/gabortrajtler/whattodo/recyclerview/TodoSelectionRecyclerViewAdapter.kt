package com.gabortrajtler.whattodo.recyclerview

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabortrajtler.whattodo.R
import com.gabortrajtler.whattodo.database.WhatTodo

class TodoSelectionRecyclerViewAdapter(
    context: Context,
    private var onTodoEventListener: OnTodoEventListener
) :
    RecyclerView.Adapter<TodoSelectionViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var whatTodos = emptyList<WhatTodo>() // Cached copy of todos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoSelectionViewHolder {
        val view = inflater.inflate(R.layout.todo_selection_view_holder, parent, false)
        return TodoSelectionViewHolder(
            view, onTodoEventListener
        )
    }

    override fun getItemCount(): Int {
        return whatTodos.size
    }

    override fun onBindViewHolder(holder: TodoSelectionViewHolder, position: Int) {
        val currentTodo = whatTodos[position]
        holder.todoPosition.text = currentTodo.todoId.toString()
        holder.todoTitle.text = currentTodo.todoText
        setCompletion(holder, currentTodo)
    }

    private fun setCompletion(
        holder: TodoSelectionViewHolder,
        currentTodo: WhatTodo
    ) {
        if (currentTodo.isCompleted) {
            // Every ViewHolder instance has an itemView field, which is an instance of View.
            // Every View instance has a getContext() method; you can use this to access resources.
            holder.todoIsComplete.text = holder.itemView.context.getString(R.string.done) // "DONE"
        } else {
            holder.todoIsComplete.text = ""
        }
    }

    internal fun setTodos(whatTodos: List<WhatTodo>) {
        this.whatTodos = whatTodos

        // notify observers
        notifyDataSetChanged()
    }

    interface OnTodoEventListener {
        fun onTodoClick(position: Int)
    }

}

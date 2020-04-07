package com.gabortrajtler.whattodo.recyclerview

import android.content.Context
import android.view.LayoutInflater
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
        holder.todoTitle.text = currentTodo.todoText
        setCompletion(holder, currentTodo)
    }

    private fun setCompletion(
        holder: TodoSelectionViewHolder,
        currentTodo: WhatTodo
    ) {
        if (currentTodo.isCompleted) {
            setImagesAndColorsIfComplete(holder)
        } else {
            setImagesAndColorsByDefault(holder)
        }
    }

    private fun setImagesAndColorsByDefault(holder: TodoSelectionViewHolder) {
        holder.todoCheckBox.setImageResource(android.R.drawable.checkbox_off_background)
        holder.itemView.setBackgroundResource(R.color.custColorDarkRed)
    }

    private fun setImagesAndColorsIfComplete(holder: TodoSelectionViewHolder) {
        holder.todoCheckBox.setImageResource(android.R.drawable.checkbox_on_background)
        holder.itemView.setBackgroundResource(R.color.colorPrimaryDark)
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

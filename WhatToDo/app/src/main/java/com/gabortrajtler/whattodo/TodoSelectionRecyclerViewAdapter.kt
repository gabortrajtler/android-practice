package com.gabortrajtler.whattodo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoSelectionRecyclerViewAdapter(private val whatTodoTitles: MutableList<String>) : RecyclerView.Adapter<TodoSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoSelectionViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.todo_selection_view_holder, parent, false)

        return TodoSelectionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return whatTodoTitles.size
    }

    override fun onBindViewHolder(holder: TodoSelectionViewHolder, position: Int) {
        if (holder != null) {
            holder.todoPosition.text = position.toString()
            holder.todoTitle.text = whatTodoTitles[position]
        }
    }

}

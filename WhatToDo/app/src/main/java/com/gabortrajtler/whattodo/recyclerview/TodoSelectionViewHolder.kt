package com.gabortrajtler.whattodo.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabortrajtler.whattodo.R

class TodoSelectionViewHolder(
    itemView: View,
    onTodoEventListener: TodoSelectionRecyclerViewAdapter.OnTodoEventListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    // binding views to ViewHolder
    val todoPosition = itemView.findViewById<TextView>(R.id.itemNumber) as TextView
    val todoTitle = itemView.findViewById<TextView>(R.id.itemString) as TextView
    val todoIsComplete = itemView.findViewById<TextView>(R.id.itemIsCompleted) as TextView
    lateinit var onTodoEventListener: TodoSelectionRecyclerViewAdapter.OnTodoEventListener

    init {
        itemView.setOnClickListener(this)
        this.onTodoEventListener = onTodoEventListener
    }

    override fun onClick(v: View?) {
        onTodoEventListener.onTodoClick(adapterPosition)
    }
}
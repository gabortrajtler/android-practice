package com.gabortrajtler.whattodo.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabortrajtler.whattodo.R

class TodoSelectionViewHolder(
    itemView: View,
    onTodoEventListener: TodoSelectionRecyclerViewAdapter.OnTodoEventListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    // binding views to ViewHolder
    val todoCheckBox = itemView.findViewById<TextView>(R.id.checkboxImage) as ImageView
    val todoTitle = itemView.findViewById<TextView>(R.id.itemString) as TextView
    var onTodoEventListener: TodoSelectionRecyclerViewAdapter.OnTodoEventListener

    init {
        itemView.setOnClickListener(this)
        this.onTodoEventListener = onTodoEventListener
    }

    override fun onClick(view: View?) {
        onTodoEventListener.onTodoClick(adapterPosition)
    }
}
package com.gabortrajtler.whattodo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // binding views to ViewHolder
    val todoPosition = itemView.findViewById<TextView>(R.id.itemNumber) as TextView
    val todoTitle = itemView.findViewById<TextView>(R.id.itemString) as TextView
}
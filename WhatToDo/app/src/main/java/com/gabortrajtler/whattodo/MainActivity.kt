package com.gabortrajtler.whattodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var todoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoRecyclerView = findViewById(R.id.todo_recycleview)
        todoRecyclerView.layoutManager = LinearLayoutManager(this)
        todoRecyclerView.adapter = TodoSelectionRecyclerViewAdapter()
    }
}

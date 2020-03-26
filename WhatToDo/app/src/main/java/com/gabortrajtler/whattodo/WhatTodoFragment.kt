package com.gabortrajtler.whattodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabortrajtler.whattodo.database.WhatTodo


class WhatTodoFragment: Fragment() {

    lateinit var todoRecyclerView: RecyclerView
    lateinit var addTodoButton: Button
    lateinit var addTodoEdit: EditText
    lateinit var whatTodoViewModel: WhatTodoViewModel
    lateinit var adapter: TodoSelectionRecyclerViewAdapter

    // 1. onCreateView, this is a must to give back the UI
    // findViewbyId's were too early here - gave nullPointers
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todos, container, false)
    }

    // 2. onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TodoSelectionRecyclerViewAdapter(this.requireContext())
        whatTodoViewModel = ViewModelProvider(this).get(WhatTodoViewModel::class.java)
        addTodoButton = view.findViewById(R.id.add_todo_button)
        todoRecyclerView = view.findViewById(R.id.todo_recycleview)
        addTodoEdit = view.findViewById(R.id.add_todo_edit)
        createRecycleView()

/*        whatTodoViewModel.allTodos.observe(viewLifecycleOwner, Observer { todos ->  //TODO check
            // Update the cached copy of the words in the adapter.
            todos?.let { adapter.setTodos(it) }
        })*/

        setListenerAddTodoButton()
/*
        // Hide the keyboard
        val imm = ContextCompat.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)*/
    }

    private fun setListenerAddTodoButton() {
        addTodoButton.setOnClickListener { addTodo(it) }
    }

    private fun addTodo(view: View?) {
        val whatTodo = WhatTodo(todoText = addTodoEdit.text.toString())
        whatTodoViewModel.insert(whatTodo)

        Toast.makeText(requireContext(), "Added: ${addTodoEdit.text}", Toast.LENGTH_SHORT).show()

/*        // Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)*/
    }


    private fun createRecycleView() {
        todoRecyclerView.layoutManager = LinearLayoutManager(activity)
        todoRecyclerView.adapter = adapter  // TODO check
    }


}
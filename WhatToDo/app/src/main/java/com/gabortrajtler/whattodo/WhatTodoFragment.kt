package com.gabortrajtler.whattodo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabortrajtler.whattodo.database.WhatTodo
import com.gabortrajtler.whattodo.recyclerview.TodoSelectionRecyclerViewAdapter


class WhatTodoFragment : Fragment(), TodoSelectionRecyclerViewAdapter.OnTodoEventListener {

    lateinit var todoRecyclerView: RecyclerView
    lateinit var addTodoButton: Button
    lateinit var addTodoEdit: EditText
    lateinit var whatTodoViewModel: WhatTodoViewModel
    lateinit var adapter: TodoSelectionRecyclerViewAdapter

    // 1. onCreateView, this is a must to give back the UI
    // findViewbyId's were too early here - gave nullPointers
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todos, container, false)
    }

    // 2. onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter =
            TodoSelectionRecyclerViewAdapter(
                this.requireContext(), this
            )
        whatTodoViewModel = ViewModelProvider(this).get(WhatTodoViewModel::class.java)
        findViews(view)
        setRecycleView()
        observeWhatTodos()
        setListenerAddTodoButton()
    }

    private fun findViews(view: View) {
        addTodoButton = view.findViewById(R.id.add_todo_button)
        todoRecyclerView = view.findViewById(R.id.todo_recycleview)
        addTodoEdit = view.findViewById(R.id.add_todo_edit)
    }

    private fun observeWhatTodos() {
        // Observe LiveData<List<WhatTodo>> in VM (<- DB) and change the RecycleView Adapter accordingly
        whatTodoViewModel.allTodos.observe(viewLifecycleOwner, Observer { todos ->
            // Update the cached copy of the todos in the adapter.
            todos?.let { adapter.setTodos(it) }
        })
    }

    private fun setListenerAddTodoButton() {
        addTodoButton.setOnClickListener { addTodo(it) }
    }

    private fun addTodo(view: View?) {
        val addTodoText = addTodoEdit.text.toString()
        val allWhatTodos = adapter.whatTodos
        if (addTodoText.isNotEmpty()) {
            val whatTodo =
                WhatTodo(todoText = addTodoText, todoId = adapter.itemCount)
            var contains = false
            for (todo in allWhatTodos) {
                if (todo.todoText == whatTodo.todoText) {
                    contains = true
                }
            }
            if(!contains) {
                whatTodoViewModel.insert(whatTodo)
            }
        }

        hideKeyboard()
        clearTodoEdit()
    }

    private fun clearTodoEdit() {
        addTodoEdit.text.clear()
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun setRecycleView() {
        todoRecyclerView.layoutManager = LinearLayoutManager(activity)
        todoRecyclerView.adapter = adapter
    }

    override fun onTodoClick(position: Int) {
        val whatTodo = adapter.whatTodos.get(position)
        if (whatTodo.isCompleted) {
            whatTodoViewModel.deleteTodo(whatTodo.todoText)
        } else {
            whatTodoViewModel.completeTodo(whatTodo.todoText)
        }
    }


}
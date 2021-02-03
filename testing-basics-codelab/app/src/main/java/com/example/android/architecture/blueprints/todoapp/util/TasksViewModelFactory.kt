package com.example.android.architecture.blueprints.todoapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.tasks.TasksViewModel

@Suppress("UNCHECKED_CAST")
class TasksViewModelFactory(
        private val tasksRepository: TasksRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            (TasksViewModel(tasksRepository) as T)
}
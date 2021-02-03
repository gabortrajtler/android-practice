package com.example.android.architecture.blueprints.todoapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailViewModel

@Suppress("UNCHECKED_CAST")
class TaskDetailViewModelFactory (
        private val tasksRepository: TasksRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            (TaskDetailViewModel(tasksRepository) as T)
}
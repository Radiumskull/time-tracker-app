package com.pawnation.timetracker.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pawnation.timetracker.database.TaskDatabase
import com.pawnation.timetracker.database.TaskRepository
import com.pawnation.timetracker.models.Task

class TasksViewModel(application: Application) : AndroidViewModel(application) {
    val allTasks: LiveData<List<Task>>
    private val repository: TaskRepository


    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.readAllData
    }
}
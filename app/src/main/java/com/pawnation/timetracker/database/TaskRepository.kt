package com.pawnation.timetracker.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.pawnation.timetracker.models.Task

class TaskRepository(private val taskDao: TaskDao) {
    val readAllData: LiveData<List<Task>> = taskDao.getAll()

    suspend fun addTask(task: Task){
        Log.i("DEBUG", task.taskDuration.toString())
        taskDao.insert(task)
    }
}
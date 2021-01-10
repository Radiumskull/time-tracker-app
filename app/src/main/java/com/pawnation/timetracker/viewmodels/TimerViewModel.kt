package com.pawnation.timetracker.viewmodels

import android.app.Application
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pawnation.timetracker.database.TaskDatabase
import com.pawnation.timetracker.database.TaskRepository
import com.pawnation.timetracker.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*

class TimerViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: TaskRepository


    val currTime = MutableLiveData<String>();
    val isRunning = MutableLiveData<Boolean>();

    var handler: android.os.Handler? = null
    var startTime: Long = 0L
    var currMillis: Long = 0L

    init{
        isRunning.value = false
        currTime.value = "00:00"
        handler = android.os.Handler(Looper.myLooper() ?: Looper.getMainLooper())
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
    }

    fun formatTime(time: Int): String{
        val temp: String = when(time.toInt().toString().length){
            1 -> "0" + time.toInt().toString()
            else -> time.toString()
        }
        return temp
    }

    fun startTimer(){
        isRunning.value = true
        startTime = SystemClock.uptimeMillis()
        handler?.postDelayed(runnable, 0)

    }

    fun cancelTimer(){
        handler?.removeCallbacks(runnable)
        isRunning.value = false
        addTask(Task(taskDate = Date.from(Instant.now()).toString(), taskDuration = currMillis))
    }

    var runnable: Runnable = object: Runnable{
        override fun run() {
            currMillis = SystemClock.uptimeMillis() - startTime
            var seconds = (currMillis/ 1000).toInt()
            val minutes =  (seconds / 60) % 60
            val hours = (seconds / (60 * 60))
            seconds = seconds % 60

            val timeString = "${formatTime(hours)}:${formatTime(minutes)}:${formatTime(seconds)}"
            currTime.value = timeString
            handler?.postDelayed(this, 0)
        }
    }


    private fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }
}
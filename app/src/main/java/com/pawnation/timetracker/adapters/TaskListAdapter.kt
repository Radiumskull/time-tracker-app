package com.pawnation.timetracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pawnation.timetracker.R
import com.pawnation.timetracker.models.Task

class TaskListAdapter: RecyclerView.Adapter<TaskListAdapter.MyViewHolder>() {
    private var taskList = emptyList<Task>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun setData(currTask: Task) {
            var seconds = (currTask.taskDuration/ 1000).toInt()
            val minutes =  (seconds / 60) % 60
            val hours = (seconds / (60 * 60))
            seconds = seconds % 60
            var timeString = ""
            if(hours > 0) timeString += "${hours} hrs "
            if(minutes > 0) timeString += "${minutes} mins "
            timeString += "${seconds} secs"
            itemView.findViewById<TextView>(R.id.task_id).text = currTask.taskId.toInt().toString()
            itemView.findViewById<TextView>(R.id.task_date).text = currTask.taskDate
            itemView.findViewById<TextView>(R.id.task_duration).text = timeString
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: TaskListAdapter.MyViewHolder, position: Int) {
        val currTask = taskList[position]
        return holder.setData(currTask)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(it: List<Task>) {
        taskList = it
        notifyDataSetChanged()
    }
}
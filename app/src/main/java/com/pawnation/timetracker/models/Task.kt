package com.pawnation.timetracker.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0L,

    @ColumnInfo(name = "task_duration")
    val taskDuration: Long,

    @ColumnInfo(name = "task_date")
    val taskDate: String
)
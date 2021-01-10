package com.pawnation.timetracker.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pawnation.timetracker.models.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query(value = "SELECT * FROM tasks ORDER BY taskId DESC")
    fun getAll(): LiveData<List<Task>>

}
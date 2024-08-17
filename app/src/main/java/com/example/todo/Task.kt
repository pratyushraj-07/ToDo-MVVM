package com.example.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskState(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("task")
    val task: String,
    @ColumnInfo("date")
    val date: String = "",
    @ColumnInfo("is_completed")
    var isCompleted: Boolean = false,
)
package com.example.todo

data class TaskState(
    val id: Long = 0L,
    val task: String,
    val date: String = "",
    //val time : String? = "",
    var isCompleted: Boolean = false,
)
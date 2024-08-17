package com.example.todo

data class Task(
    val id: Long = 0,
    val task: String,
    val date: String = "",
    var isCompleted: Boolean = false,
)
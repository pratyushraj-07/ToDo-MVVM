package com.example.todo

sealed class TaskEvent {
    data class AddTask(val task: String, val date: String) : TaskEvent()
    data class EditTask(val id: Long, val newTitle: String, val newDate:String) : TaskEvent()
    data class MarkAsComplete(val id: Long) : TaskEvent()
    data class DeleteTask(val id: Long) : TaskEvent()
}
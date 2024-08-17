package com.example.todo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {

    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList: StateFlow<List<Task>> = _taskList

    fun onEvent(event: TaskEvent) {
        when (event) {

            is TaskEvent.AddTask -> {
                addTask(event.task, event.date)
            }

            is TaskEvent.DeleteTask -> {
                deleteTask(event.id)
            }

            is TaskEvent.EditTask -> {
                editTask(event.id, event.newTitle, event.newDate)
            }

            is TaskEvent.MarkAsComplete -> {
                markDone(event.id)
            }

        }

    }

    private fun markDone(id: Long) {
        val completedTask = _taskList.value.map {task->
            if(task.id == id){
                task.copy(isCompleted = !task.isCompleted)
            }else
                task
        }
        _taskList.value = completedTask
    }

    private fun editTask(id: Long, newTitle: String, newDate:String) {
        val editedTask = _taskList.value.map { task ->
            if (task.id == id) {
                task.copy(task = newTitle, date = newDate)
            } else
                task
        }
        _taskList.value = editedTask

    }

    private fun deleteTask(id: Long) {
        _taskList.value = _taskList.value.filter { it.id != id }
    }

    private fun addTask(task: String, date: String) {
        val newId = (_taskList.value.maxOfOrNull { it.id } ?: 0) + 1
        val newTask = Task(id = newId, task = task, date = date)
        _taskList.value += newTask
    }

}
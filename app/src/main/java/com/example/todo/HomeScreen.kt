package com.example.todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.todo.component.TaskItem
import com.example.todo.component.TopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    event: (TaskEvent) -> Unit,
    viewModel: TaskViewModel
) {
    val taskList by viewModel.taskList.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var currentEditTaskId by remember { mutableStateOf<Long?>(null) }
    var taskTitle by remember { mutableStateOf("") }
    var taskDate by remember{ mutableStateOf("") }
   // val totalToDo = taskList.size

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        topBar = {
            TopBar(
                title = "ToDo",
                showNavigationIcon = false, onBackNavClick = {/*Nothing bcoz icon is false*/ },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                shape = RoundedCornerShape(30.dp),
                elevation = FloatingActionButtonDefaults.elevation(5.dp),
                contentColor = Color.Black,
                containerColor = Color.Green,
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = 40.dp, end = 20.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = colorResource(id = R.color.DarkSlate))
        )
        {
            if (taskList.isEmpty()) {
                item {
                    LottieScreen()
                }
            }
            else {
                items(taskList) { task ->
                    TaskItem(
                        title = task.task,
                        onEditClick = { id, title, date ->
                            currentEditTaskId = id
                            taskTitle = title
                            taskDate = date
                            showEditDialog = true
                        },
                        task = task,
                        date = task.date,
                        onDeleteClick = { id ->
                            event(TaskEvent.DeleteTask(id = id))
                            //viewModel.onEvent(TaskEvent.DeleteTask(id))
                        },
                        onCompleteClick = { id ->
                            event(TaskEvent.MarkAsComplete(id = id))
                            //task.isCompleted = true
                        })
                }
            }
        }
    }

    if (showEditDialog) {
        EditTaskDialog(
            initialDate = taskDate,
            initialTitle = taskTitle,
            onDismissRequest = { showEditDialog = false },
            onEditComplete = { editedtitle, newDate ->
                currentEditTaskId?.let { id ->
                    event(TaskEvent.EditTask(id, editedtitle, newDate))
                    //viewModel.onEvent(TaskEvent.EditTask(id, title))
                }
            })
    }

    if (showAddDialog) {
        AddTaskDialog(
            onDismissRequest = { showAddDialog = false },
            onConfirmation = { title, selectedDate ->
                event(TaskEvent.AddTask(title, selectedDate))
                //viewModel.onEvent(TaskEvent.AddTask(title))
                showAddDialog = false
            })

    }
}

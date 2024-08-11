package com.example.todo

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.todo.component.DatePicker

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTaskDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String) -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    //For Date picker...
    var selectedDate by remember { mutableStateOf("") }
    var showDateDialog by remember { mutableStateOf(false) }

    DatePicker(onDismiss = { showDateDialog = false }, showDialog = showDateDialog,
        onConfirm = { date -> selectedDate = date }
    )

    AlertDialog(
        properties = DialogProperties(dismissOnClickOutside = false),
        containerColor = colorResource(id = R.color.DarkSlate),
        title = { Text(text = "Add Task", fontWeight = FontWeight.Bold) },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (title.isNotEmpty()) {
                            onConfirmation(title, selectedDate)
                            onDismissRequest()
                        } else {
                            Toast.makeText(context, "Enter something to do", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                {
                    Text(text = "Add")
                }

                Button(onClick = { onDismissRequest() }) { Text(text = "Cancel") }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            {
                OutlinedTextField(
                    placeholder = { Text(text = "New to-do") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    singleLine = true,
                    value = title,
                    onValueChange = { title = it }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = selectedDate,
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { Text(text = "Schedule Date") }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        onClick = { showDateDialog = !showDateDialog },
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Schedule",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
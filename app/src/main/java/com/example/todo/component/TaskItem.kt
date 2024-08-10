package com.example.todo.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.R
import com.example.todo.TaskState

@Composable
fun TaskItem(
    title: String,
    date:String,
    task: TaskState,
    onCompleteClick:(Long)->Unit,
    onDeleteClick: (Long) -> Unit,
    onEditClick: (Long, String, String) -> Unit
) {
    val isCompleted = task.isCompleted
    val containerColor by animateColorAsState(
        targetValue = if(isCompleted) colorResource(id = R.color.Dgreen)
                      else colorResource(id = R.color.grey),
        label = "",
        animationSpec = tween(durationMillis = 600, easing = LinearEasing)
    )

    Card(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
            .wrapContentSize(),
        onClick = { onEditClick(task.id, title, date) },
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Row(
            modifier = Modifier.padding(end = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            IconButton(onClick = { onCompleteClick(task.id) }) {
                Icon(
                    painter =
                    if (isCompleted) painterResource(id = R.drawable.baseline_check_box_24)
                    else painterResource(id = R.drawable.baseline_check_box_outline_blank_24),
                    contentDescription = ""
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            )
            {

                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = date.ifEmpty { "Today" },
                    color = Color.White,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
            }

            IconButton(onClick = { onDeleteClick(task.id) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
            }
        }
    }
}
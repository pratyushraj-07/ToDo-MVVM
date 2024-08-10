package com.example.todo.component

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(
    onConfirm:(String)->Unit
){
    var selectedDate by remember{ mutableStateOf("") }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val date = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
            val formatter = DateTimeFormatter.ofPattern("EEE, MMMM dd, yyyy")
            selectedDate = date.format(formatter)
            onConfirm(selectedDate)
        }, year, month, day
    )
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    datePickerDialog.datePicker.minDate = calendar.timeInMillis
    //datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 86400000-- For prevention of selecting future dates.
    datePickerDialog.show()
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun TimePickerDial(
//    onConfirm:(String)->Unit
//){
//    val context = LocalContext.current
//    var selectedTime by remember{ mutableStateOf("") }
//    val calendar = Calendar.getInstance()
//    val hour = calendar.get(Calendar.HOUR_OF_DAY)
//    val minute = calendar.get(Calendar.MINUTE)
//
//    TimePickerDialog(
//        context,
//        {_, selectedHour, selectedMinute->
//            selectedTime = LocalTime.of(selectedHour, selectedMinute).toString()
//            val selectedMinutes = selectedHour * 60 + selectedMinute
//            val currentMinute = hour * 60 + minute
//
//            onConfirm(selectedTime)
//        }, hour, minute,
//        false
//    ).apply { show() }
//}
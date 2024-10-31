package com.stu71954.jobroomdb_71954


import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(date)
}

fun showDatePicker(context: Context, calendar: Calendar, onDateSelected: (Calendar) -> Unit) {
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            onDateSelected(selectedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    datePickerDialog.datePicker.minDate = System.currentTimeMillis() // Block dates before today
    datePickerDialog.show()
}
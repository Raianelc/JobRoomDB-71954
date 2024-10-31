package com.stu71954.jobroomdb_71954

import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

// I separated the date formatting functions into a separate file to keep the code organized and easy to maintain
// Function to format a Date object into a string with the pattern "yyyy-MM-dd"
fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(date)
}

// Function to show a date picker dialog and return the selected date
fun showDatePicker(context: Context, calendar: Calendar, onDateSelected: (Calendar) -> Unit) {
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            // Create a new Calendar instance with the selected date
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            // Pass the selected date to the callback function
            onDateSelected(selectedDate)
        },
        // Initialize the date picker with the current date
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    // Set the minimum date to today to block dates before today
    datePickerDialog.datePicker.minDate = System.currentTimeMillis()
    // Show the date picker dialog
    datePickerDialog.show()
}
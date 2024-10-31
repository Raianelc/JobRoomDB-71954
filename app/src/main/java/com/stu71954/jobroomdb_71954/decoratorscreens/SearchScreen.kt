package com.stu71954.jobroomdb_71954.decoratorscreens

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stu71954.jobroomdb_71954.Decorator
import com.stu71954.jobroomdb_71954.mockDecorators
import com.stu71954.jobroomdb_71954.screens.CustomTextField
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    // State management using remember
    var location by remember { mutableStateOf("") }
    // State management using rememberSaveable
    var dateFrom by rememberSaveable { mutableStateOf(Calendar.getInstance()) }
    var dateTo by rememberSaveable { mutableStateOf(Calendar.getInstance()) }
    var availableDecorators by remember { mutableStateOf<List<Decorator>>(emptyList()) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Search Decorators") }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
                CustomTextField(value = location, onValueChange = { location = it }, label = "Location")

                // TextField for Date From
                DateInputField(
                    label = "Select Date From",
                    date = dateFrom,
                    onDateSelected = { dateFrom = it },
                    context = context
                )

                // TextField for Date To
                DateInputField(
                    label = "Select Date To",
                    date = dateTo,
                    onDateSelected = { dateTo = it },
                    context = context
                )

                Button(onClick = {
                    if (location.isNotBlank()) {
                        availableDecorators = mockDecorators.filter {
                            it.location.contains(location, ignoreCase = true) &&
                                    !it.availableFrom.before(dateFrom.time) && // Ensure availableFrom is on or after dateFrom
                                    !it.availableTo.after(dateTo.time) // Ensure availableTo is on or before dateTo
                        }
                    } else {
                        // Handle validation error
                        // For simplicity, we just clear the list if validation fails
                        availableDecorators = emptyList()
                    }
                }) {
                    Text("Search")
                }

                LazyColumn (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    items(availableDecorators) { decorator ->
                        DecoratorItem(decorator = decorator, navController = navController)
                    }
                }
            }
        }
    )
}

@Composable
fun DateInputField(label: String, date: Calendar, onDateSelected: (Calendar) -> Unit, context: Context) {
    TextField(
        value = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date.time),
        onValueChange = { },
        label = { Text(label) },
        readOnly = true, // Make it read-only to prevent typing
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        trailingIcon = {
            IconButton(onClick = {
                showDatePicker(context, date) { selectedDate ->
                    onDateSelected(selectedDate)
                }
            }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Select Date")
            }
        }
    )
}

private fun showDatePicker(context: Context, calendar: Calendar, onDateSelected: (Calendar) -> Unit) {
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

@Composable
fun DecoratorItem(decorator: Decorator, navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Text(
                text = decorator.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Location: ${decorator.location}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Available From: ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(decorator.availableFrom)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Available To: ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(decorator.availableTo)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("details/${decorator.id}") },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("See More Details", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
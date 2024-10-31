package com.stu71954.jobroomdb_71954.decoratorscreens

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.stu71954.jobroomdb_71954.data.Decorator
import com.stu71954.jobroomdb_71954.data.mockDecorators
import com.stu71954.jobroomdb_71954.formatDate
import com.stu71954.jobroomdb_71954.showDatePicker
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    // State management using remember
    var location by remember { mutableStateOf("")}
    var locationError by remember { mutableStateOf(false) }
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
                CustomTextField(
                    value = location,
                    onValueChange = {
                        location = it
                        locationError = location.isBlank()
                    },
                    label = "Location",
                    isError = locationError
                )
                if (locationError) {
                    Text("Location cannot be empty", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                }

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
                    locationError = location.isBlank()
                    if (!locationError) {
                        availableDecorators = mockDecorators.filter {
                            it.location.contains(location, ignoreCase = true) &&
                                    !it.availableFrom.before(dateFrom.time) && // Ensure availableFrom is on or after dateFrom
                                    !it.availableTo.after(dateTo.time) // Ensure availableTo is on or before dateTo
                        }
                    } else {
                        // Handle validation error
                        // For simplicity, just clear the list if validation fails
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
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = isError,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    )
}

@Composable
fun DateInputField(label: String, date: Calendar, onDateSelected: (Calendar) -> Unit, context: Context) {
    TextField(
        value = formatDate(date.time),
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
            DecoratorText(text = decorator.name, style = MaterialTheme.typography.bodyMedium)
            DecoratorText(text = "Location: ${decorator.location}", style = MaterialTheme.typography.bodySmall)
            DecoratorText(text = "Available From: ${formatDate(decorator.availableFrom)}", style = MaterialTheme.typography.bodySmall)
            DecoratorText(text = "Available To: ${formatDate(decorator.availableTo)}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            SeeMoreButton(navController, decorator.id)
        }
    }
}

@Composable
fun DecoratorText(text: String, style: TextStyle) {
    Text(
        text = text,
        style = style,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun SeeMoreButton(navController: NavHostController, decoratorId: Int) {
    Button(
        onClick = { navController.navigate("details/$decoratorId") },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text("See More Details", color = MaterialTheme.colorScheme.onPrimary)
    }
}
package com.stu71954.jobroomdb_71954.decoratorscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stu71954.jobroomdb_71954.data.Decorator
import com.stu71954.jobroomdb_71954.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DecoratorDetails(decorator: Decorator) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Decorator Details") }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
                Text("Name: ${decorator.name}")
                Text("Location: ${decorator.location}")
                Text("Email: ${decorator.email}")
                Text("Contact: ${decorator.contact}")
                Text("Available From: ${formatDate(decorator.availableFrom)}")
                Text("Available To: ${formatDate(decorator.availableTo)}")
            }
        }
    )
}
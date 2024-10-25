package com.stu71954.jobroomdb_71954

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import java.util.*

@Composable
fun JobPage(jobViewModel: JobViewModel = viewModel()) {
    val jobs by jobViewModel.allJobs.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        InputForm(onAddJob = { jobViewModel.insert(it) })
        Spacer(modifier = Modifier.height(16.dp))
        JobDetails(jobs = jobs)
    }
}

@Composable
fun InputForm(onAddJob: (JobData) -> Unit) {
    var customerName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var jobType by remember { mutableStateOf("") }
    var dateFrom by remember { mutableStateOf(Date()) }
    var dateTo by remember { mutableStateOf(Date()) }

    Column {
        TextField(value = customerName, onValueChange = { customerName = it }, label = { Text("Customer Name") })
        TextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
        TextField(value = jobType, onValueChange = { jobType = it }, label = { Text("Job Type") })
        // Add date pickers for dateFrom and dateTo

        Button(onClick = {
            val jobs = JobData(customerName = customerName, location = location, jobType = jobType, dateFrom = dateFrom, dateTo = dateTo)
            onAddJob(jobs)
        }) {
            Text("Add Job")
        }
    }
}

@Composable
fun JobDetails(jobs: List<JobData>) {
    LazyColumn {
        items(jobs) { job ->
            Text(text = "${job.customerName} - ${job.location} - ${job.jobType} - ${job.dateFrom} - ${job.dateTo}")
        }
    }
}
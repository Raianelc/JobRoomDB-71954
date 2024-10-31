package com.stu71954.jobroomdb_71954.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stu71954.jobroomdb_71954.data.JobData
import com.stu71954.jobroomdb_71954.viewmodel.JobViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobPage(jobViewModel: JobViewModel = viewModel()) {
    val jobs by jobViewModel.allJobs.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Job Management") }
            )
        },
        content = { paddingValues ->
            JobPageContent(
                jobs = jobs,
                onAddJob = { jobViewModel.insert(it) },
                onDeleteJob = { jobViewModel.delete(it) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            )
        }
    )
}

@Composable
fun JobPageContent(
    jobs: List<JobData>,
    onAddJob: (JobData) -> Unit,
    onDeleteJob: (JobData) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputForm(onAddJob = onAddJob)
        Spacer(modifier = Modifier.height(16.dp))
        JobDetails(jobs = jobs, onDelete = onDeleteJob)
    }
}

@Composable
fun InputForm(onAddJob: (JobData) -> Unit) {
    var customerName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var jobType by remember { mutableStateOf("") }
    var dateFrom by remember { mutableStateOf(Date()) }
    var dateTo by remember { mutableStateOf(Date()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CustomTextField(value = customerName, onValueChange = { customerName = it }, label = "Customer Name")
        CustomTextField(value = location, onValueChange = { location = it }, label = "Location")
        CustomTextField(value = jobType, onValueChange = { jobType = it }, label = "Job Type")
        // Add date pickers for dateFrom and dateTo

        Button(
            onClick = {
                val job = JobData(
                    customerName = customerName,
                    location = location,
                    jobType = jobType,
                    dateFrom = dateFrom,
                    dateTo = dateTo
                )
                onAddJob(job)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Job")
        }
    }
}

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, label: String
                    ) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun JobDetails(jobs: List<JobData>, onDelete: (JobData) -> Unit) {
    LazyColumn {
        items(jobs) { job ->
            JobItem(job = job, onDelete = onDelete)
        }
    }
}

@Composable
fun JobItem(job: JobData, onDelete: (JobData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "${job.customerName} - ${job.location} - ${job.jobType} - ${job.dateFrom} - ${job.dateTo}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onDelete(job) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Unspecified)
            ) {
                Text("Delete", color = Color.White)
            }
        }
    }
}



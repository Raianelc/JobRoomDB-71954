package com.stu71954.jobroomdb_71954.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu71954.jobroomdb_71954.data.JobData
import com.stu71954.jobroomdb_71954.db.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class JobViewModel(private val repository: Repository) : ViewModel() {
    val allJobs: Flow<List<JobData>> = repository.allJobs

    fun insert(job: JobData) = viewModelScope.launch {
        repository.insert(job)
    }

    fun delete(job: JobData) = viewModelScope.launch {
        repository.delete(job)
    }
}
package com.stu71954.jobroomdb_71954.dbinventory

import com.stu71954.jobroomdb_71954.data.JobData
import com.stu71954.jobroomdb_71954.db.JobDao
import kotlinx.coroutines.flow.Flow

class Repository(private val jobDao: JobDao) {
    val allJobs: Flow<List<JobData>> = jobDao.getAllJobs()

    suspend fun insert(job: JobData) {
        jobDao.insertJob(job)
    }

    suspend fun delete(job: JobData) {
        jobDao.deleteJob(job)
    }
}
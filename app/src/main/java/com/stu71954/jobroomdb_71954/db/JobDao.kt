package com.stu71954.jobroomdb_71954.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stu71954.jobroomdb_71954.JobData
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Query("SELECT * FROM jobs")
    fun getAllJobs(): Flow<List<JobData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobData)

    @Delete
    suspend fun deleteJob(job: JobData)
}
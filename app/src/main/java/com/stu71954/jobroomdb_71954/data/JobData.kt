package com.stu71954.jobroomdb_71954.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "jobs")
data class JobData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val customerName: String,
    val location: String,
    val jobType: String,
    val dateFrom: Date,
    val dateTo: Date
)


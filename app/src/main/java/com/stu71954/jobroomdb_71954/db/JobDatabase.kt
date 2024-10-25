package com.stu71954.jobroomdb_71954.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stu71954.jobroomdb_71954.JobData

@Database(entities = [JobData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class JobDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}
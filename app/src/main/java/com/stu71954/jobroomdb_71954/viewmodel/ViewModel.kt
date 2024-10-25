package com.stu71954.jobroomdb_71954.viewmodel

import Factory
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.stu71954.jobroomdb_71954.db.JobDatabase
import com.stu71954.jobroomdb_71954.db.Repository

fun initializeViewModel(context: Context): JobViewModel {
    val database = Room.databaseBuilder(
        context.applicationContext,
        JobDatabase::class.java, "job-database"
    ).build()
    val repository = Repository(database.jobDao())
    return ViewModelProvider(context as ComponentActivity, Factory(repository))[JobViewModel::class.java]
}
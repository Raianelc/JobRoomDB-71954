package com.stu71954.jobroomdb_71954

import Factory
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.stu71954.jobroomdb_71954.db.JobDatabase
import com.stu71954.jobroomdb_71954.db.Repository
import com.stu71954.jobroomdb_71954.ui.theme.JobRoomDB71954Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = Room.databaseBuilder(
            applicationContext,
            JobDatabase::class.java, "job-database"
        ).build()
        val repository = Repository(database.jobDao())
        val jobViewModel: JobViewModel by lazy {
            ViewModelProvider(this, Factory(repository)).get(JobViewModel::class.java)
        }
        setContent {
            JobRoomDB71954Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    JobPage(jobViewModel)
                }
            }
        }
    }
}
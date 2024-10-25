package com.stu71954.jobroomdb_71954

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.stu71954.jobroomdb_71954.ui.theme.JobRoomDB71954Theme
import com.stu71954.jobroomdb_71954.viewmodel.initializeViewModel

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val jobViewModel = initializeViewModel(this)
        setContent {
            JobRoomDB71954Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    JobPage(jobViewModel)
                }
            }
        }
    }
}
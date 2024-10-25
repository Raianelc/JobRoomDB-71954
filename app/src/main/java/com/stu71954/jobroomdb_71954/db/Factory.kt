import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stu71954.jobroomdb_71954.viewmodel.JobViewModel
import com.stu71954.jobroomdb_71954.db.Repository

class Factory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


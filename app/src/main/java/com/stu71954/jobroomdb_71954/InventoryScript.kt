package com.stu71954.jobroomdb_71954

import android.content.Context
import com.stu71954.jobroomdb_71954.dbinventory.DatabaseProvider
import kotlinx.coroutines.runBlocking

fun getTotalMonetaryWorth(context: Context): Double {
    val db = DatabaseProvider.getDatabase(context)
    val inventoryDao = db.inventoryDao()
    return runBlocking {
        inventoryDao.getTotalInventoryValue()
    }
}
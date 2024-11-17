package com.stu71954.jobroomdb_71954

import android.content.Context
import com.stu71954.jobroomdb_71954.dbinventory.DatabaseProvider
import kotlinx.coroutines.runBlocking



// Function to get the total monetary worth of the inventory
fun getTotalMonetaryWorth(context: Context): Double {
    // Get the database instance
    val db = DatabaseProvider.getDatabase(context)
    // Get the InventoryDao instance
    val inventoryDao = db.inventoryDao()
    // Run a coroutine to get the total inventory value
    return runBlocking {
        inventoryDao.getTotalInventoryValue()
    }
}
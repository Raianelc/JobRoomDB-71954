package com.stu71954.jobroomdb_71954.dbinventory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stu71954.jobroomdb_71954.viewmodel.Inventory

@Dao
interface InventoryDao {

    @Insert
    suspend fun insertInventory(inventory: Inventory)

    @Query("SELECT SUM(quantity * cost_per_unit) FROM Inventory")
    fun getTotalInventoryValue(): Double
}
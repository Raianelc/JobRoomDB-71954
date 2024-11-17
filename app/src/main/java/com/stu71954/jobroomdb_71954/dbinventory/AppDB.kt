package com.stu71954.jobroomdb_71954.dbinventory

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stu71954.jobroomdb_71954.viewmodel.Inventory

@Database(entities = [Inventory::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}


package com.stu71954.jobroomdb_71954.viewmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory")
data class Inventory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "supplier", defaultValue = "Co-op Store") val supplier: String = "Co-op Store",
    @ColumnInfo(name = "cost_per_unit") val costPerUnit: Double
)
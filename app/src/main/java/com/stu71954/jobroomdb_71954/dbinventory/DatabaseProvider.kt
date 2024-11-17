package com.stu71954.jobroomdb_71954.dbinventory

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db: AppDB? = null

    fun getDatabase(context: Context): AppDB {
        if (db == null) {
            db = Room.databaseBuilder(
                context.applicationContext,
                AppDB::class.java, "inventory-database"
            ).build()
        }
        return db!!
    }
}
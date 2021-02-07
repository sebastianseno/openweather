package com.dodolife.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dodolife.weather.database.dao.StudentDao
import com.dodolife.weather.database.entity.StudentDb

@Database(
    entities = [StudentDb::class],
    version = 3
)

abstract class KraDb : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    fun clearAndResetAllTables() {
        // reset all auto-incrementalValues
        val query = SimpleSQLiteQuery("DELETE FROM sqlite_sequence")

        runInTransaction {
            clearAllTables()
            query(query)
        }
    }
}
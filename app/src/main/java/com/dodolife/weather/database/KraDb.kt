package com.dodolife.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dodolife.weather.database.dao.WeatherDao
import com.dodolife.weather.database.entity.WeatherDb

@Database(
    entities = [WeatherDb::class],
    version = 1
)

abstract class KraDb : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    fun clearAndResetAllTables() {
        // reset all auto-incrementalValues
        val query = SimpleSQLiteQuery("DELETE FROM sqlite_sequence")

        runInTransaction {
            clearAllTables()
            query(query)
        }
    }
}
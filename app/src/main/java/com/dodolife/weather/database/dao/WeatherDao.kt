package com.dodolife.weather.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dodolife.weather.database.entity.WeatherDb

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWeatherData(obj: WeatherDb)

    @Query("SELECT * FROM WeatherDb WHERE description LIKE :name")
    fun searchStudentsName(name: String?): LiveData<List<WeatherDb>>

    @Query("DELETE FROM WeatherDb WHERE feelsLike = :nim")
    fun deleteStudent(nim: Long)

}
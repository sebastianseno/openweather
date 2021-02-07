package com.dodolife.weather.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dodolife.weather.database.entity.StudentDb

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudentData(obj: StudentDb)

    @Query("SELECT * FROM StudentDb WHERE name LIKE :name")
    fun searchStudentsName(name: String?): LiveData<List<StudentDb>>

    @Query("DELETE FROM StudentDb WHERE nim = :nim")
    fun deleteStudent(nim: Long)

}
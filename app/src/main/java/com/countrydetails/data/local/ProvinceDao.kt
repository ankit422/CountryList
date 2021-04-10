package com.countrydetails.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.countrydetails.data.entities.Province

@Dao
interface ProvinceDao {
    @Query("SELECT * FROM province WHERE CountryCode = :code")
    fun getAll(code: String?): LiveData<List<Province>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(provinces: List<Province>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(province: Province)
}
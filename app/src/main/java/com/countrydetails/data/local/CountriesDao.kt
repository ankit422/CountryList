package com.countrydetails.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.countrydetails.data.entities.Country

@Dao
interface CountriesDao {
    @Query("SELECT * FROM country")
    fun getAll(): LiveData<List<Country>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<Country>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: Country)

    @Query("SELECT * FROM country WHERE ID = :id")
    fun getCountry(id: Int): LiveData<Country>
}
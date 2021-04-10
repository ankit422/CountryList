package com.countrydetails.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.countrydetails.data.entities.Country
import com.countrydetails.data.entities.Province

@Database(entities = [Country::class, Province::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countriesDao(): CountriesDao
    abstract fun provinceDao(): ProvinceDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "countries")
                .fallbackToDestructiveMigration()
                .build()
    }

}
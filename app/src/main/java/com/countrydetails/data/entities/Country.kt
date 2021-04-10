package com.countrydetails.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class Country(
    @PrimaryKey val ID: Int,
    var Name: String?,
    var Code: String?,
    var PhoneCode: String?,
)
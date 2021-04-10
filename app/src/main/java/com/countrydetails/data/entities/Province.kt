package com.countrydetails.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "province")
data class Province(
    @PrimaryKey val ID: Int,
    var Name: String?,
    var Code: String?,
    var CountryCode: String?,
)
package com.countrydetails.data.repository

import com.countrydetails.data.local.CountriesDao
import com.countrydetails.data.local.ProvinceDao
import com.countrydetails.data.remote.ArticleRemoteDataSource
import com.countrydetails.utils.performGetOperation
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteDataSource: ArticleRemoteDataSource,
    private val countryDataSource: CountriesDao,
    private val provinceDataSource: ProvinceDao
) {

    fun getProvince(id: Int, code: String? = null) = performGetOperation(
        databaseQuery = { provinceDataSource.getAll(code) },
        networkCall = { remoteDataSource.getProvinces(id) },
        saveCallResult = { provinceDataSource.insertAll(it) }
    )

    fun getCountries() = performGetOperation(
        databaseQuery = { countryDataSource.getAll() },
        networkCall = { remoteDataSource.getCountries() },
        saveCallResult = { countryDataSource.insertAll(it) }
    )
}
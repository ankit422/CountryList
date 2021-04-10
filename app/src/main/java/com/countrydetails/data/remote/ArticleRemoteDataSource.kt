package com.countrydetails.data.remote

import javax.inject.Inject

class ArticleRemoteDataSource @Inject constructor(
    private val networkService: NetworkService
) : BaseDataSource() {

    suspend fun getCountries() = getResult { networkService.getAllCountries() }
    suspend fun getProvinces(id:Int) = getResult { networkService.getAllProvinces(id) }
}
package com.countrydetails.data.remote

import com.countrydetails.data.entities.Country
import com.countrydetails.data.entities.Province
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("rest/worldregions/country")
    suspend fun getAllCountries(): Response<List<Country>>

    @GET("rest/worldregions/country/{id}/province")
    suspend fun getAllProvinces(@Path("id") id: Int): Response<List<Province>>
}
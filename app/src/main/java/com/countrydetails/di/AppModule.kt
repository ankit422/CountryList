package com.countrydetails.di

import android.content.Context
import com.countrydetails.data.local.AppDatabase
import com.countrydetails.data.remote.ArticleRemoteDataSource
import com.countrydetails.data.remote.NetworkService
import com.countrydetails.data.repository.DataRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.countrydetails.data.local.CountriesDao
import com.countrydetails.data.local.ProvinceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://connect.mindbodyonline.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCountryService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)

    @Singleton
    @Provides
    fun provideCountryRemoteDataSource(networkService: NetworkService) =
        ArticleRemoteDataSource(networkService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCountryDao(db: AppDatabase) = db.countriesDao()

    @Singleton
    @Provides
    fun provideProvinceDao(db: AppDatabase) = db.provinceDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: ArticleRemoteDataSource,
        countryDataSource: CountriesDao,
        provinceDataSource: ProvinceDao
    ) =
        DataRepository(remoteDataSource, countryDataSource, provinceDataSource)
}
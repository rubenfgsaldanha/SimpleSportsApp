package com.example.sporteventsapp.di

import com.example.sporteventsapp.common.Constants.BASE_URL
import com.example.sporteventsapp.data.remote.api.SportApi
import com.example.sporteventsapp.data.remote.datasource.SportsRemoteDataSource
import com.example.sporteventsapp.data.remote.datasource.SportsRemoteDataSourceImpl
import com.example.sporteventsapp.data.repository.SportsRepository
import com.example.sporteventsapp.data.repository.SportsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSportApi(): SportApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SportApi::class.java)

    @Provides
    @Singleton
    fun provideSportsDataSource(api: SportApi): SportsRemoteDataSource =
        SportsRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideSportsRepository(dataSource: SportsRemoteDataSource): SportsRepository =
        SportsRepositoryImpl(dataSource)

}
package com.example.sporteventsapp.data.remote.datasource

import com.example.sporteventsapp.data.remote.dto.SportDto

interface SportsRemoteDataSource {

    suspend fun getSports(): List<SportDto>?

}
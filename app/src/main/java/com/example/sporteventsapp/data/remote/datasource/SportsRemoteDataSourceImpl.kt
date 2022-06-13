package com.example.sporteventsapp.data.remote.datasource

import com.example.sporteventsapp.data.remote.api.SportApi
import com.example.sporteventsapp.data.remote.dto.SportDto
import javax.inject.Inject

class SportsRemoteDataSourceImpl @Inject constructor(
    private val api: SportApi
): SportsRemoteDataSource {

    companion object{
        const val GET_SPORTS_URL = "/api/sports"
    }

    override suspend fun getSports(): List<SportDto>? {
        return api.getSports(GET_SPORTS_URL)
    }

}
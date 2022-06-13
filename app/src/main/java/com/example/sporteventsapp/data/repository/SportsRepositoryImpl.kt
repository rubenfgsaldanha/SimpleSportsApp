package com.example.sporteventsapp.data.repository

import com.example.sporteventsapp.data.remote.datasource.SportsRemoteDataSource
import com.example.sporteventsapp.data.remote.dto.toSportList
import com.example.sporteventsapp.domain.model.Sport
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val dataSource: SportsRemoteDataSource
): SportsRepository {

    override suspend fun getSports(): List<Sport> {
        val result = dataSource.getSports()
        return result?.toSportList() ?: emptyList()
    }

}
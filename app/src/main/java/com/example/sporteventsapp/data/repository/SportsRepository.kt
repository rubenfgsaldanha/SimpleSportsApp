package com.example.sporteventsapp.data.repository

import com.example.sporteventsapp.domain.model.Sport

interface SportsRepository {

    suspend fun getSports(): List<Sport>

}
package com.example.sporteventsapp.data.remote.api

import com.example.sporteventsapp.data.remote.dto.SportDto
import retrofit2.http.GET
import retrofit2.http.Url

interface SportApi {

    @GET
    suspend fun getSports(
        @Url url: String
    ): List<SportDto>?

}
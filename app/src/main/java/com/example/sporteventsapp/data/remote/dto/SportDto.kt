package com.example.sporteventsapp.data.remote.dto

import com.example.sporteventsapp.domain.model.Sport
import com.google.gson.annotations.SerializedName

data class SportDto(
    @SerializedName("i") val id: String?,
    @SerializedName("d") val name: String?,
    @SerializedName("e") val events: List<EventDto>?
)

fun List<SportDto>.toSportList() =
    map { sport ->
        Sport(
            id = sport.id,
            name = sport.name,
            events = sport.events?.map { it.toEvent() }
        )
    }
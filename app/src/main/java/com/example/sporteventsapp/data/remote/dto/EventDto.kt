package com.example.sporteventsapp.data.remote.dto

import com.example.sporteventsapp.domain.model.Event
import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("i") val eventId: String?,
    @SerializedName("si") val sportId: String?,
    @SerializedName("d") val name: String?,
    @SerializedName("tt") val startTime: Long?
)


fun EventDto.toEvent() =
    Event(
        eventId = eventId,
        sportId = sportId,
        name = name,
        startTime = startTime
    )
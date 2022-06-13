package com.example.sporteventsapp.domain.model

data class Event(
    val eventId: String?,
    val sportId: String?,
    val name: String?,
    val startTime: Long?,
    var homeTeam: String? = null,
    var awayTeam: String? = null,
    var isFavourite: Boolean = false
)

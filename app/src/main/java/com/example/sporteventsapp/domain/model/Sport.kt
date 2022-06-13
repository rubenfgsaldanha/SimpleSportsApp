package com.example.sporteventsapp.domain.model

data class Sport(
    val id: String?,
    val name: String?,
    var events: List<Event>?,
    var isExpanded: Boolean = true
)

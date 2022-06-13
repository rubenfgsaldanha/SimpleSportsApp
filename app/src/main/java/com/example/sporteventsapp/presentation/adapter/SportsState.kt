package com.example.sporteventsapp.presentation.adapter

import com.example.sporteventsapp.domain.model.Sport

sealed class SportsState{

    data class SportsReceived(val sports: List<Sport>): SportsState()

    data class InfoChanged(val sports: List<Sport>, val position: Int): SportsState()

}

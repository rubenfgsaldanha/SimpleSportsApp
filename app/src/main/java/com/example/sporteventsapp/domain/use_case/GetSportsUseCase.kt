package com.example.sporteventsapp.domain.use_case

import com.example.sporteventsapp.data.repository.SportsRepository
import com.example.sporteventsapp.domain.model.Sport
import javax.inject.Inject

class GetSportsUseCase @Inject constructor(
    private val repository: SportsRepository
) {

    suspend operator fun invoke(): List<Sport>{
        val sports = repository.getSports()

        sports.forEach { sport ->
            sport.events?.forEach { event ->
                val homeAndAwayTeam = event.name?.split(" - ")
                event.homeTeam = homeAndAwayTeam?.get(0)
                event.awayTeam = homeAndAwayTeam?.get(1)
            }
        }

        return sports
    }

}
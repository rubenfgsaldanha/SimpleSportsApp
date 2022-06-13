package com.example.sporteventsapp.domain.use_case

import com.example.sporteventsapp.domain.model.Event
import javax.inject.Inject

class SortEventsUseCase @Inject constructor(){

    operator fun invoke(unsortedList: List<Event>): List<Event>{
        return unsortedList.sortedByDescending { it.isFavourite }
    }
}
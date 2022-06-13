package com.example.sporteventsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sporteventsapp.domain.model.Sport
import com.example.sporteventsapp.domain.use_case.GetSportsUseCase
import com.example.sporteventsapp.domain.use_case.SortEventsUseCase
import com.example.sporteventsapp.presentation.adapter.SportsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val getSportsUseCase: GetSportsUseCase,
    private val sortEventsUseCase: SortEventsUseCase
): ViewModel() {


    private val _sportsList = MutableLiveData<SportsState>()
    val sportsList: LiveData<SportsState> = _sportsList

    private var sports = listOf<Sport>()

    init {
        getSports()
    }


    private fun getSports(){
        viewModelScope.launch(Dispatchers.IO) {
            sports = getSportsUseCase()
            _sportsList.postValue(SportsState.SportsReceived(sports))
        }
    }


    fun onExpandCollapseClicked(id: String?, position: Int){
        val sport = sports.find { it.id == id }
        sport?.isExpanded = !(sport?.isExpanded ?: true)
        _sportsList.postValue(SportsState.InfoChanged(sports, position))
    }


    fun onFavouriteClicked(sportId: String?, eventId: String?, sportPosition: Int){
        val sport = sports.find { it.id == sportId }
        val event = sport?.events?.find { it.eventId == eventId }

        event?.let {
            it.isFavourite = !it.isFavourite

            val sortedEvents = sortEventsUseCase(sport.events!!)
            sport.events = sortedEvents

            _sportsList.postValue(SportsState.InfoChanged(sports, sportPosition))
        }
    }

}
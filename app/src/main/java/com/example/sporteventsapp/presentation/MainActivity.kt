package com.example.sporteventsapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sporteventsapp.databinding.ActivityMainBinding
import com.example.sporteventsapp.domain.model.Sport
import com.example.sporteventsapp.presentation.adapter.SportsAdapter
import com.example.sporteventsapp.presentation.adapter.SportsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val viewModel: SportsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setObservers()
    }


    private fun setObservers(){
        viewModel.sportsList.observe(this) { event ->

            when(event){

                is SportsState.SportsReceived -> {
                    populateSportsList(event.sports)
                }

                is SportsState.InfoChanged -> {
                    (binding?.sportsList?.adapter as? SportsAdapter)?.updateSports(event.sports, event.position)
                }

            }

        }
    }


    private fun populateSportsList(sports: List<Sport>) {
        binding?.sportsList?.adapter = SportsAdapter(
            context = this,
            sports = sports,
            onExpandCollapseClicked = viewModel::onExpandCollapseClicked,
            onFavouriteClicked = viewModel::onFavouriteClicked
        )
        binding?.sportsList?.setItemViewCacheSize(15)
    }

}
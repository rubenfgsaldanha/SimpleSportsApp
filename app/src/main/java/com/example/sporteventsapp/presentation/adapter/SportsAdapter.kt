package com.example.sporteventsapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sporteventsapp.R
import com.example.sporteventsapp.databinding.SportsItemBinding
import com.example.sporteventsapp.domain.model.Sport

class SportsAdapter(
    private val context: Context,
    private var sports: List<Sport>,
    private val onExpandCollapseClicked: (String?, Int) -> Unit,
    private val onFavouriteClicked: (String?, String?, Int) -> Unit
): RecyclerView.Adapter<SportsAdapter.SportsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SportsViewHolder(
            SportsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) =
        holder.bind(sports[position], position)


    override fun getItemCount() = sports.size


    fun updateSports(sports: List<Sport>, position: Int){
        this.sports = sports
        notifyItemChanged(position)
    }


    inner class SportsViewHolder(
        private val binding: SportsItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(sport: Sport, position: Int){
            binding.sportName.text = sport.name
            binding.eventsList.visibility = if(sport.isExpanded) View.VISIBLE else View.GONE

            val drawableId = if(sport.isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
            binding.expandCollapseIcon.setImageDrawable(ContextCompat.getDrawable(context, drawableId))

            binding.expandCollapseIcon.setOnClickListener {
                onExpandCollapseClicked(sport.id, position)
            }

            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val adapter = EventsAdapter(
                context = context,
                sportPosition = position,
                events = sport.events ?: emptyList(),
                onFavouriteClicked = onFavouriteClicked
            )

            binding.eventsList.layoutManager = layoutManager
            binding.eventsList.adapter = adapter
        }

    }

}

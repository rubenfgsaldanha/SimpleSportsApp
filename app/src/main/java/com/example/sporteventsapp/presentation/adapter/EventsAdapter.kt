package com.example.sporteventsapp.presentation.adapter

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sporteventsapp.R
import com.example.sporteventsapp.databinding.EventItemBinding
import com.example.sporteventsapp.domain.model.Event
import java.util.*
import java.util.concurrent.TimeUnit

class EventsAdapter(
    private val context: Context,
    private val events: List<Event>,
    private val sportPosition: Int,
    private val onFavouriteClicked: (String?, String?, Int) -> Unit
): RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsViewHolder(
            EventItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: EventsViewHolder, position: Int){
        val event = events[position]
        handleCountDownTimerLogic(holder, event)
        holder.bind(event)
    }


    override fun getItemCount() = events.size


    private fun handleCountDownTimerLogic(holder: EventsViewHolder, event: Event) {
        holder.timer?.cancel()

        val eventStartTime = (event.startTime ?: 0) * 1000
        val currentTime = Date().time
        val countDownValue = eventStartTime - currentTime
        holder.binding.startEventCountdown.text = formatTimer(countDownValue)

        holder.timer = object : CountDownTimer(countDownValue, 1000){
            override fun onTick(p0: Long) {
                holder.binding.startEventCountdown.text = formatTimer(p0)
            }

            override fun onFinish() {
                val timerText = "00:00:00"
                holder.binding.startEventCountdown.text = timerText
            }
        }.start()
    }


    private fun formatTimer(countDownValue: Long): String{
        val hours = TimeUnit.MILLISECONDS.toHours(countDownValue)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(countDownValue) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(countDownValue))
        val seconds = TimeUnit.MILLISECONDS.toSeconds(countDownValue) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(countDownValue))

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }


    inner class EventsViewHolder(
        val binding: EventItemBinding,
        var timer: CountDownTimer? = null
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(event: Event){
            binding.homeTeam.text = event.homeTeam
            binding.awayTeam.text = event.awayTeam

            val drawable = if(event.isFavourite) R.drawable.ic_star_filled else R.drawable.ic_star_outline
            binding.favouriteImage.setImageDrawable(ContextCompat.getDrawable(context, drawable))

            binding.favouriteImage.setOnClickListener {
                onFavouriteClicked(event.sportId, event.eventId, sportPosition)
            }
        }

    }

}
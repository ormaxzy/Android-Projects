package com.example.cityweather.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.databinding.ItemWeatherBinding
import com.example.cityweather.models.Weather
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AllWeatherAdapter(private var data: List<Weather> = emptyList()) :
    RecyclerView.Adapter<AllWeatherAdapter.AllWeatherViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(updatedWeatherList: MutableList<Weather>) {
        data = updatedWeatherList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllWeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllWeatherViewHolder, position: Int) {
        val weather = data[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int = data.size


    inner class AllWeatherViewHolder(
        private val binding: ItemWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(weather: Weather) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val currentDate = Date(weather.date * 1000L)
            val formattedDate = dateFormat.format(currentDate)
            binding.dateTextView.text = formattedDate
            if (weather.temperature > 0) {
                binding.temperatureTextView.text = "+${weather.temperature.toInt()}"
            } else {
                binding.temperatureTextView.text = "${weather.temperature.toInt()}"
            }
        }
    }
}


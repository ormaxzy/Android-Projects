package com.example.cityweather.presentation.citiesDetailsScreen

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.App
import com.example.cityweather.R
import com.example.cityweather.adapters.AllWeatherAdapter
import com.example.cityweather.models.Weather
import com.example.cityweather.presentation.factory.CityDetailsViewModelFactory

class CityDetailsFragment : Fragment() {

    private val allWeatherAdapter = AllWeatherAdapter()
    private val args: CityDetailsFragmentArgs by navArgs()

    private val viewModel: CityDetailsViewModel by viewModels {
        CityDetailsViewModelFactory((requireActivity().application as App).appComponent.provideWeatherRepository())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_city_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = view.findViewById<TextView>(R.id.cityName)
        val backBtn = view.findViewById<ImageButton>(R.id.backBtn)
        val weatherRecyclerView = view.findViewById<RecyclerView>(R.id.weather_recycler_view)
        val city = args.city
        cityName.text = city

        viewModel.searchWeather(city)
        viewModel.loadWeatherDB(city)

        viewModel.weatherAPIData.observe(viewLifecycleOwner, Observer { weather ->
            val updatedWeatherList = mutableListOf<Weather>()
            updatedWeatherList.add(0, weather)
            allWeatherAdapter.setData(updatedWeatherList)
            weatherRecyclerView.adapter = allWeatherAdapter
        })

        viewModel.weatherData.observe(viewLifecycleOwner) { weatherList ->
            val updatedWeatherList = mutableListOf<Weather>()
            weatherList?.let {
                updatedWeatherList.addAll(it)
            }
            Log.d("@@@", "updatedWeatherList: $updatedWeatherList")
            allWeatherAdapter.setData(updatedWeatherList)
            weatherRecyclerView.adapter = allWeatherAdapter
        }

        backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
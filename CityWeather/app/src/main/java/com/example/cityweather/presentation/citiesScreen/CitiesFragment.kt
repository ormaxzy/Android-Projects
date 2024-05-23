package com.example.cityweather.presentation.citiesScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.App

import com.example.cityweather.R
import com.example.cityweather.adapters.SearchHistoryAdapter
import com.example.cityweather.presentation.factory.CitiesViewModelFactory


class CitiesFragment : Fragment() {

    private lateinit var searchHistoryAdapter: SearchHistoryAdapter
    private val viewModel: CitiesViewModel by viewModels {
        CitiesViewModelFactory((requireActivity().application as App).appComponent.provideWeatherRepository())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val citiesRecyclerView = view.findViewById<RecyclerView>(R.id.names_recycler_view)
        searchHistoryAdapter = SearchHistoryAdapter { name ->
            val action = CitiesFragmentDirections.actionCitiesFragment2ToCityDetailsFragment(name)
            findNavController().navigate(action)
        }
        viewModel.cities.observe(viewLifecycleOwner, Observer { cities ->
            searchHistoryAdapter.setData(cities.reversed())
            citiesRecyclerView.adapter = searchHistoryAdapter
        })
    }


}
package com.example.cityweather.presentation.searchScreen


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cityweather.App
import com.example.cityweather.adapters.SearchHistoryAdapter
import com.example.cityweather.databinding.FragmentSearchBinding
import com.example.cityweather.presentation.factory.WeatherViewModelFactory


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var searchText: String = ""
    private var searchHistory: MutableList<String> = mutableListOf()
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter

    private val viewModel: SearchViewModel by viewModels {
        WeatherViewModelFactory((requireActivity().application as App).appComponent.provideWeatherRepository())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchHistoryAdapter = SearchHistoryAdapter { searchText ->
            val action =
                SearchFragmentDirections.actionSearchFragment2ToCityDetailsFragment(searchText)
            findNavController().navigate(action)
        }
        binding.searchHistoryRecyclerView.adapter = searchHistoryAdapter

        loadSearchHistoryFromPreferences()

        binding.searchButton.setOnClickListener {
            val city = binding.cityEditText.text.toString()
            searchText = city
            if (searchText != "") {
                searchHistory.add(0, searchText)
                if (searchHistory.size > 10) {
                    searchHistory.removeLast()
                }
                searchHistoryAdapter.setData(searchHistory)
                saveSearchHistoryToPreferences()
                binding.cityEditText.setText("")
                val action =
                    SearchFragmentDirections.actionSearchFragment2ToCityDetailsFragment(city)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Request is empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveSearchHistoryToPreferences() {
        val sharedPreferences =
            requireContext().getSharedPreferences("search_preferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putStringSet("search_history", searchHistory.toSet())
            apply()
        }
    }

    private fun loadSearchHistoryFromPreferences() {
        val sharedPreferences =
            requireContext().getSharedPreferences("search_preferences", Context.MODE_PRIVATE)
        val savedSearchHistory = sharedPreferences.getStringSet("search_history", emptySet())
        searchHistory.clear()
        savedSearchHistory?.let {
            searchHistory.addAll(it)
            searchHistoryAdapter.setData(searchHistory)
        }
    }
}


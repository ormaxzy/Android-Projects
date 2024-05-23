package com.example.cityweather.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.databinding.ItemHistoryBinding

class SearchHistoryAdapter(
    private var searchHistory: List<String> = emptyList(),
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHistoryViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val item = searchHistory.getOrNull(position)
        holder.binding.nameTextView.text = item
        holder.binding.root.setOnClickListener { holder.onItemClick(item.toString()) }
    }

    override fun getItemCount(): Int = searchHistory.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<String>) {
        this.searchHistory = data
        notifyDataSetChanged()
    }


    inner class SearchHistoryViewHolder(
        val binding: ItemHistoryBinding,
        val onItemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
    }
}

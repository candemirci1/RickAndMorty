package com.example.rickandmorty.ui.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.model.Episode
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.util.DateHelper

class EpisodeAdapter(
    private val episodes: List<Episode>,
    private val itemWidth: Int
):RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    class EpisodeViewHolder(val binding: ItemEpisodeBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeAdapter.EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val currentEpisode = episodes[position]
        holder.binding.apply {
            rootLayout.updateLayoutParams {
                width = itemWidth
                height = itemWidth
            }
            tvEpisode.text = currentEpisode.name
            val dateHelper = DateHelper()
            tvCreated.text = dateHelper.simpleCreated(currentEpisode.created)




        }
    }

    override fun getItemCount(): Int {
       return episodes.size
    }
}
package com.example.rickandmorty.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.data.model.EpisodeDTO
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.databinding.FragmentHomeBinding
import com.example.rickandmorty.ui.location.LocationAdapter
import com.example.rickandmorty.ui.location.LocationFragment
import com.example.rickandmorty.ui.location.LocationListViewState
import com.example.rickandmorty.ui.location.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment: Fragment() {
    private val viewModel: EpisodeViewModel by viewModels()

    private var episodeAdapter: EpisodeAdapter? = null
    private var binding: FragmentEpisodeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is EpisodeListViewState.Success -> {
                        binding?.loading?.isVisible = false
                        it.data?.let {episodeDTO ->
                            val width = getScreenWidth()
                            episodeAdapter = EpisodeAdapter(episodeDTO.results,width/ EpisodeFragment.SPAN_COUNT)

                            binding?.rvEpisode?.layoutManager = GridLayoutManager(requireContext(), LocationFragment.SPAN_COUNT)
                            binding?.rvEpisode?.adapter = episodeAdapter

                        }


                    }

                    is EpisodeListViewState.Error -> {
                        binding?.loading?.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    is EpisodeListViewState.Loading -> {
                        binding?.loading?.isVisible = true
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun getScreenWidth(): Int {
        val displayMetrics = requireContext().resources.displayMetrics
        return displayMetrics.widthPixels

    }

    companion object {
        const val SPAN_COUNT = 2
    }
}
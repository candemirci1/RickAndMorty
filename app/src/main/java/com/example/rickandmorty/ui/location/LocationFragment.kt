package com.example.rickandmorty.ui.location

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
import com.example.rickandmorty.databinding.FragmentLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment: Fragment() {

    private val viewModel: LocationViewModel by viewModels()

    private var locationAdapter: LocationAdapter? = null

    private var binding: FragmentLocationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is LocationListViewState.Success -> {
                        binding?.loading?.isVisible = false
                        it.data?.let {locationDTO ->
                            val width = getScreenWidth()
                            locationAdapter = LocationAdapter(locationDTO.results,width/ SPAN_COUNT)

                            binding?.rvLocation?.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
                            binding?.rvLocation?.adapter = locationAdapter

                        }


                    }

                    is LocationListViewState.Error -> {
                        binding?.loading?.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    is LocationListViewState.Loading -> {
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
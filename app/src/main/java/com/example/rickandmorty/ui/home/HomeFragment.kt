package com.example.rickandmorty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var homeAdapter: HomeAdapter? = null

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is HomeListViewState.Success -> {
                        binding?.loading?.isVisible = false
                        it.data?.let { charactersDTO ->
                            val width = getScreenWidth()
                            homeAdapter = HomeAdapter(charactersDTO.characters,width/ SPAN_COUNT) {
                                val action =
                                    HomeFragmentDirections.actionHomeFragmentToCharacterInfoFragment(it)
                                findNavController().navigate(action)
                            }

                            binding?.rvHome?.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
                            binding?.rvHome?.adapter = homeAdapter

                        }


                    }

                    is HomeListViewState.Error -> {
                        binding?.loading?.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    is HomeListViewState.Loading -> {
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
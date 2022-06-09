package com.example.rickandmorty.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.FragmentCharacterInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private val viewModel: CharacterInfoViewModel by viewModels()

    private var binding: FragmentCharacterInfoBinding? = null

    private val arg: CharacterInfoFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterInfo(arg.id)
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is CharacterInfoViewState.Success -> {
                        binding?.loading?.isVisible = false
                        it.data?.let {

                            binding?.apply {
                                Glide.with(requireContext())
                                    .load(it.image)
                                    .into(ivCharacterImage)
                                tvCharacterinfoName.text = it.name
                                tvCharacterSpeciesValue.text = it.species
                                tvCharacterStatusValue.text = it.status
                                val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
                                val simpleDateFormat = SimpleDateFormat(pattern,Locale.getDefault())
                                val date = simpleDateFormat.parse(it.created)
                                val format = "EEE, d MMM yyyy"
                                val dateFormatter = SimpleDateFormat(format,Locale.getDefault())
                                val prettyDate = dateFormatter.format(date!!)
                                tvCharacterCreatedValue.text = prettyDate

                            }


                        }


                    }
                    is CharacterInfoViewState.Error -> {
                        binding?.loading?.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                    }
                    is CharacterInfoViewState.Loading -> {
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

}
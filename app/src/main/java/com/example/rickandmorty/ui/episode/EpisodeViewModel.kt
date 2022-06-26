package com.example.rickandmorty.ui.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.EpisodeDTO
import com.example.rickandmorty.data.repository.EpisodeRepository
import com.example.rickandmorty.data.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository
): ViewModel() {

    val state = MutableStateFlow<EpisodeListViewState>(EpisodeListViewState.Loading)

    init {
        getEpisode()

    }

    private fun getEpisode() {
        viewModelScope.launch {
            episodeRepository.getEpisode().let {
                when(it){
                    is Response.Loading -> state.value = EpisodeListViewState.Loading
                    is Response.Success -> state.value = EpisodeListViewState.Success(it.data)
                    is Response.Error -> state.value = EpisodeListViewState.Error(it.message.orEmpty())
                }
            }
        }
    }
}


sealed class EpisodeListViewState {
    object Loading : EpisodeListViewState()

    data class Success(
        val data: EpisodeDTO?
    ): EpisodeListViewState()

    data class Error(
        val message: String
    ) : EpisodeListViewState()

}
package com.example.rickandmorty.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.CharactersDTO
import com.example.rickandmorty.data.repository.CharactersRepository
import com.example.rickandmorty.data.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    val state = MutableStateFlow<HomeListViewState>(HomeListViewState.Loading)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            charactersRepository.getCharacters().let {
                when(it) {
                    is Response.Loading -> state.value = HomeListViewState.Loading
                    is Response.Success -> state.value = HomeListViewState.Success(it.data)
                    is Response.Error -> state.value = HomeListViewState.Error(it.message.orEmpty())
                }
            }
        }
    }
}




sealed class HomeListViewState {
    object Loading : HomeListViewState()

    data class Success(
        val data: CharactersDTO?
    ): HomeListViewState()

    data class Error(
        val message: String
    ) : HomeListViewState()

}
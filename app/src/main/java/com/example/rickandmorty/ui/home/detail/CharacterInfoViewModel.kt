package com.example.rickandmorty.ui.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.repository.CharactersRepository
import com.example.rickandmorty.data.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(

    private val characterRepository: CharactersRepository
) : ViewModel() {

    val state = MutableStateFlow<CharacterInfoViewState>(CharacterInfoViewState.Loading)

    fun getCharacterInfo(id: Int) {
        viewModelScope.launch {
            characterRepository.getCharactersInfo(id).let {
                when(it) {
                    is Response.Loading -> state.value = CharacterInfoViewState.Loading
                    is Response.Success -> state.value = CharacterInfoViewState.Success(it.data)
                    is Response.Error -> state.value = CharacterInfoViewState.Error(it.message.orEmpty())
                }
            }
        }
    }


}


sealed class CharacterInfoViewState {
    object Loading : CharacterInfoViewState()

    data class Success(
        val data: Character?
    ): CharacterInfoViewState()

    data class Error(
        val message: String
    ) : CharacterInfoViewState()

}
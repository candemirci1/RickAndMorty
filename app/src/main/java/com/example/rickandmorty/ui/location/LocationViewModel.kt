package com.example.rickandmorty.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.CharactersDTO
import com.example.rickandmorty.data.model.LocationDTO
import com.example.rickandmorty.data.repository.LocationRepository
import com.example.rickandmorty.data.utils.Response
import com.example.rickandmorty.ui.home.HomeListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor (
    private val locationRepository: LocationRepository
    ): ViewModel() {

    val state = MutableStateFlow<LocationListViewState>(LocationListViewState.Loading)

    init {
        getLocations()

    }

    private fun getLocations() {
        viewModelScope.launch {
            locationRepository.getLocation().let {
                when(it) {
                    is Response.Loading -> state.value = LocationListViewState.Loading
                    is Response.Success -> state.value = LocationListViewState.Success(it.data)
                    is Response.Error -> state.value = LocationListViewState.Error(it.message.orEmpty())
                }
            }
        }
    }

}


sealed class LocationListViewState {
    object Loading : LocationListViewState()

    data class Success(
        val data: LocationDTO?
    ): LocationListViewState()

    data class Error(
        val message: String
    ) : LocationListViewState()

}
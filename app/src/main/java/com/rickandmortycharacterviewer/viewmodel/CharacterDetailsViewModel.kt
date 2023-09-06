package com.rickandmortycharacterviewer.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.domain.repository.RickyAndMortyRepository
import com.rickandmortycharacterviewer.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val rickyAndMortyRepository: RickyAndMortyRepository,
): ViewModel() {
    private val _characterDetailsState = mutableStateOf<Response<CharacterDetails>>(Response.Success(null))
    val characterDetailsState: State<Response<CharacterDetails>> = _characterDetailsState

    fun getCharacterDetails(id: Int) {
        viewModelScope.launch {
            rickyAndMortyRepository.getCharacterDetails(id).collect { response ->
                _characterDetailsState.value = response
            }
        }
    }
}
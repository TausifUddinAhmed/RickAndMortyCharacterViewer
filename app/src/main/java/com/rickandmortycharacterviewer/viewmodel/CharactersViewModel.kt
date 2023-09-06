package com.rickandmortycharacterviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.domain.model.CharacterResponse
import com.rickandmortycharacterviewer.domain.repository.RickyAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    rickyAndMortyRepository: RickyAndMortyRepository,
): ViewModel() {

    val characterListState: Flow<PagingData<CharacterDetails>> =
        rickyAndMortyRepository.getAllCharacter().cachedIn(viewModelScope)
}
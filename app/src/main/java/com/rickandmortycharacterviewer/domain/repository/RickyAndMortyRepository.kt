package com.rickandmortycharacterviewer.domain.repository

import androidx.paging.PagingData
import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.utils.Response
import kotlinx.coroutines.flow.Flow

interface RickyAndMortyRepository {
    fun getAllCharacter(): Flow<PagingData<CharacterDetails>>
    fun getCharacterDetails(id: Int): Flow<Response<CharacterDetails>>
}
package com.rickandmortycharacterviewer.domain.repository

import androidx.paging.PagingData
import com.rickandmortycharacterviewer.domain.model.Character
import com.rickandmortycharacterviewer.utils.Response
import kotlinx.coroutines.flow.Flow

interface RickyAndMortyRepository {
    fun getAllCharacter(): Flow<PagingData<Character>>
    fun getCharacterDetails(id: Int): Flow<Response<Character>>
}
package com.rickandmortycharacterviewer.repository_test

import androidx.paging.PagingData
import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.domain.repository.RickyAndMortyRepository
import com.rickandmortycharacterviewer.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharacterListRepository : RickyAndMortyRepository {
    override fun getAllCharacter(): Flow<PagingData<CharacterDetails>> {

        val characterResult = CharacterDetails("2017-11-04T19:26:56.301Z",
            emptyList(), "Male", 5, "https://rickandmortyapi.com/api/character/avatar/5.jpeg", null, "Jerry Smith", null, "Human", "Alive", "title1", "https://rickandmortyapi.com/api/character/5")
        val listOfCharacters = listOf<CharacterDetails>(characterResult)
       // val moviesToInsert = CharacterResponse("","" , 2,listOfCharacters )
        return flow { emit(PagingData.from(data = listOfCharacters)) }

    }

    override fun getCharacterDetails(id: Int): Flow<Response<CharacterDetails>> {
        val characterResult = CharacterDetails("2017-11-04T19:26:56.301Z",
            emptyList(), "Male", 5, "https://rickandmortyapi.com/api/character/avatar/5.jpeg", null, "Morty Smith", null, "Human", "Alive", "title1", "https://rickandmortyapi.com/api/character/5")
         return flow { emit(Response.Success<CharacterDetails>(null).copy(data = characterResult))}
    }
}

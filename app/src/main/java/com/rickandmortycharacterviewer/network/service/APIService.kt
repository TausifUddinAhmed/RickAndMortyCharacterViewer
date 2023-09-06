package com.rickandmortycharacterviewer.network.service

import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.domain.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ) : CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterDetailsById(
        @Path("id") id: Int,
    ) : CharacterDetails
}
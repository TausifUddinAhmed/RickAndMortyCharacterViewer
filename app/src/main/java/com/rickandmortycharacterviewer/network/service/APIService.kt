package com.rickandmortycharacterviewer.network.service

import com.rickandmortycharacterviewer.domain.model.Character
import com.rickandmortycharacterviewer.domain.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("games")
    suspend fun getAllGames(
        @Query("key") key: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ) : CharacterResponse

    @GET("games/{id}")
    suspend fun getGamesDetail(
        @Path("id") id: Int,
        @Query("key") key: String,
    ) : Character
}
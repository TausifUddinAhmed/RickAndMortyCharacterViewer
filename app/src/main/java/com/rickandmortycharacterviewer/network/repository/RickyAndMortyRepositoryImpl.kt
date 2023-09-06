package com.rickandmortycharacterviewer.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.domain.repository.RickyAndMortyRepository
import com.rickandmortycharacterviewer.network.pagingsource.APIPagingSource
import com.rickandmortycharacterviewer.network.service.APIService
import com.rickandmortycharacterviewer.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickyAndMortyRepositoryImpl @Inject constructor(
    private val APIService: APIService,
    private val pageSize: Int,
    private val apiKey: String,
): RickyAndMortyRepository {
    override fun getAllCharacter(): Flow<PagingData<CharacterDetails>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            APIPagingSource(
                response = { pageNext ->
                    APIService.getAllCharacters(
                        page = pageNext,
                        pageSize = pageSize,
                    )
                }
            )
        }
    ).flow

    override fun getCharacterDetails(id: Int): Flow<Response<CharacterDetails>> = flow{
        try {
            emit(Response.Loading)
            val responseApi = APIService.getCharacterDetailsById(
                id = id
            )
            emit(Response.Success(responseApi))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}
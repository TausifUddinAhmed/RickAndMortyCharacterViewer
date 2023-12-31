package com.rickandmortycharacterviewer.network.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.domain.model.CharacterResponse

class APIPagingSource(
    private val response: suspend (Int) -> CharacterResponse,
) : PagingSource<Int, CharacterDetails>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterDetails>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDetails> {
        return try {
            val nextPage = params.key ?: 1
            val gamesList = response.invoke(nextPage)
            LoadResult.Page(
                data = gamesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = gamesList.results.lastOrNull()?.id!! + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
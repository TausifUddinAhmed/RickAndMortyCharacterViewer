package com.rickandmortycharacterviewer.network.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rickandmortycharacterviewer.domain.model.Character
import com.rickandmortycharacterviewer.domain.model.CharacterResponse

class APIPagingSource(
    private val response: suspend (Int) -> CharacterResponse,
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPage = params.key ?: 1
            val gamesList = response.invoke(nextPage)
            LoadResult.Page(
                data = gamesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = gamesList
                    .next
                    ?.substringAfter("page=")
                    ?.substringBefore("&")
                    ?.toInt() ?: nextPage
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
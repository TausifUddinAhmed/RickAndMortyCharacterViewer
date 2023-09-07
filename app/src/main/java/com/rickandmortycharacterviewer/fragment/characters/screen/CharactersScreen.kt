package com.rickandmortycharacterviewer.fragment.characters.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.rickandmortycharacterviewer.R
import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.ui.component.ErrorButton
import com.rickandmortycharacterviewer.ui.component.LoadingCircular
import com.rickandmortycharacterviewer.domain.model.CharacterResponse
import com.rickandmortycharacterviewer.ui.component.CharacterCard
import com.rickandmortycharacterviewer.ui.theme.RickAndMortyCharacterViewerTheme

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    characterList: LazyPagingItems<CharacterDetails>? = null,
    onClickToDetailScreen: (Int) -> Unit = {},
) {
    if(characterList == null) return
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 150.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(characterList.itemCount) { index ->
            characterList[index].let { characters ->
                val id = characters?.id
                val name = characters?.name ?: ""
                val imageUrl = characters?.image?: ""
                val releaseDate = characters?.created ?: ""
                CharacterCard(
                    modifier = modifier
                        .padding(8.dp),
                    name = name,
                    imageUrl = imageUrl,
                    releaseDate = releaseDate,
                    onClickCharacter = {
                        id?.let {
                            onClickToDetailScreen.invoke(it)
                        }
                    }
                )
            }
        }
        characterList.apply {
            item(
                span = { GridItemSpan(maxLineSpan) }
            ) {
                when {
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                        LoadingCircular(
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                        ErrorButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.no_data_found),
                            onClick = {
                                retry()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RickAndMortyCharacterViewerTheme() {
        CharactersScreen()
    }
}
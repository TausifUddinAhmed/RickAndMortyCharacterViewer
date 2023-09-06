package com.rickandmortycharacterviewer.fragment.characters

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.rickandmortycharacterviewer.fragment.characters.screen.CharactersScreen
import com.rickandmortycharacterviewer.ui.theme.RickAndMortyCharacterViewerTheme
import com.rickandmortycharacterviewer.viewmodel.CharactersViewModel

@Composable
fun CharactersFragment(
    modifier: Modifier = Modifier,
    charactersViewModel: CharactersViewModel = hiltViewModel(),
    onClickToDetailScreen: (Int) -> Unit = {},
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        CharactersScreen(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                ),
            characterList = charactersViewModel.characterListState.collectAsLazyPagingItems(),
            onClickToDetailScreen = onClickToDetailScreen,
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CharactersFragmentPreview() {
    RickAndMortyCharacterViewerTheme() {
        CharactersFragment()
    }
}
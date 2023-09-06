package com.rickandmortycharacterviewer.fragment.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rickandmortycharacterviewer.R
import com.rickandmortycharacterviewer.fragment.detail.screen.DetailScreen
import com.rickandmortycharacterviewer.ui.component.ErrorButton
import com.rickandmortycharacterviewer.ui.component.LoadingCircular
import com.rickandmortycharacterviewer.ui.theme.RickAndMortyCharacterViewerTheme
import com.rickandmortycharacterviewer.utils.Response
import com.rickandmortycharacterviewer.viewmodel.CharacterDetailsViewModel

@Composable
fun DetailFragment(
    modifier: Modifier = Modifier,
    detailViewModel: CharacterDetailsViewModel = hiltViewModel(),
    id: Int = -1,
) {
    fun launch() {
        detailViewModel.getCharacterDetails(id)
    }

    launch()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(val gamesResponse = detailViewModel.characterDetailsState.value){
            is Response.Loading -> {
                LoadingCircular(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            is Response.Success -> {
                DetailScreen(
                    characterDetails = gamesResponse.data
                )
            }
            is Response.Failure -> {
                ErrorButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.error_message),
                    onClick = {
                        launch()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailFragmentPreview() {
    RickAndMortyCharacterViewerTheme() {
        DetailFragment()
    }
}
package com.rickandmortycharacterviewer.fragment.detail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.ui.component.CharacterHeader
import com.rickandmortycharacterviewer.ui.component.CharacterImageCarousel
import com.rickandmortycharacterviewer.ui.theme.RickAndMortyCharacterViewerTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    characterDetails: CharacterDetails? = null
) {
    if(characterDetails == null) return
    val scrollState = rememberScrollState()
    val name = characterDetails.name ?: ""
    val imageUrl = characterDetails.image ?: ""
    val releaseDate = characterDetails.created ?: ""
    val description = HtmlCompat
        .fromHtml(characterDetails.status ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)
        .toString()
    val listImageCarousel = mutableListOf<String>()
    /*games.backgroundImage?.let {
        listImageCarousel.add(it)
    }
    games.backgroundImageAdditional?.let {
        listImageCarousel.add(it)
    }*/
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        CharacterHeader(
            modifier = Modifier.padding(16.dp),
            imageUrl = imageUrl,
            name = name,
            releaseDate = releaseDate,
        )
        CharacterImageCarousel(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            listImage = listImageCarousel
        )
        Text(
            text = description,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    RickAndMortyCharacterViewerTheme() {
        DetailScreen()
    }
}

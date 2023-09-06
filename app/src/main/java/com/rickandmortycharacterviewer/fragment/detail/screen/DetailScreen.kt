package com.rickandmortycharacterviewer.fragment.detail.screen

import androidx.compose.foundation.layout.*
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
import com.rickandmortycharacterviewer.ui.component.CharacterDetailsRow
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
    val gender = characterDetails.gender ?: ""
    val status = characterDetails.status ?: ""
    val species = characterDetails.species ?: ""
    val origin = characterDetails.origin.name
    val location = characterDetails.location.name?:""
    var type = characterDetails.type
    if(type.isNullOrEmpty()){
        type = "Not available"
    }

     Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        CharacterHeader(
            modifier = Modifier.padding(16.dp),
            imageUrl = imageUrl,
            name = name,
            status = status,
            species = species,
        )

         CharacterDetailsRow(
             modifier = Modifier.padding(8.dp),
             "Gender",
             gender)

        CharacterDetailsRow(
            modifier = Modifier.padding(8.dp),
            "Origin",
            origin)

        CharacterDetailsRow(
            modifier = Modifier.padding(8.dp),
            "Location",
            location)

        CharacterDetailsRow(
            modifier = Modifier.padding(8.dp),
            "Type",
            type)


    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    RickAndMortyCharacterViewerTheme() {
        DetailScreen()
    }
}

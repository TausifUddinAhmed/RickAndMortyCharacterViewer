package com.rickandmortycharacterviewer.ui.component


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickandmortycharacterviewer.ui.theme.RickAndMortyCharacterViewerTheme

@Composable
fun CharacterDetailsRow(
    modifier: Modifier = Modifier,
    title: String = "",
    data: String = "",
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp
            ).weight(3f)

        )
        Text(
            text = data,

            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp
            ).weight(7f)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun CharacterDetailsRowPreview() {
    RickAndMortyCharacterViewerTheme() {
        CharacterDetailsRow()
    }
}
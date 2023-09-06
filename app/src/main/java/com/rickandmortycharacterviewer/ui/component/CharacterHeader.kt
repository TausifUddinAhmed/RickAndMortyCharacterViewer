package com.rickandmortycharacterviewer.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.rickandmortycharacterviewer.R
import com.rickandmortycharacterviewer.ui.theme.RickAndMortyCharacterViewerTheme

@Composable
fun CharacterHeader(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    name: String = "",
    status: String = "",
    species: String = ""
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (
            photoAvatar,
            nameText,
            statusText,
            speciesText,
        ) = createRefs()
        val imagePainter = rememberAsyncImagePainter(
            model = imageUrl,
            error = painterResource(id = R.drawable.ic_launcher_foreground),
        )
        Image(
            painter = imagePainter,
            contentDescription = name,
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
                .constrainAs(photoAvatar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(nameText.top)
                },
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(nameText){
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end,16.dp)
                    top.linkTo(photoAvatar.bottom,16.dp)
                    bottom.linkTo(statusText.top)
                }
        )
        Text(
            text = status,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(statusText){
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end,16.dp)
                top.linkTo(nameText.bottom, 8.dp)
                bottom.linkTo(speciesText.top)
            }
        )

        Text(
            text = species,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(speciesText){
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end,16.dp)
                top.linkTo(statusText.bottom,8.dp)
               // bottom.linkTo(speciesText.top)
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ProductHeaderPreview() {
    RickAndMortyCharacterViewerTheme() {
        CharacterHeader()
    }
}
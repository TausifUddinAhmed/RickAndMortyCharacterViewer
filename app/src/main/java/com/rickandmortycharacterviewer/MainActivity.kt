package com.rickandmortycharacterviewer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.rickandmortycharacterviewer.ui.theme.RickAndMortyCharacterViewerTheme
import com.rickandmortycharacterviewer.viewmodel.CharacterDetailsViewModel
import com.rickandmortycharacterviewer.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyCharacterViewerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    /*var charactersViewModel: CharactersViewModel = hiltViewModel()
                    var characterResponse =  charactersViewModel.characterListState.collectAsLazyPagingItems()
                    Log.e("characterResponse", characterResponse.toString())

                    var characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel()
                    characterDetailsViewModel.getCharacterDetails(2)
                    var characterDetails = characterDetailsViewModel.characterDetailsState.value
                    Log.e("characterDetails", characterDetails.toString())*/
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyCharacterViewerTheme {
        Greeting("Android")
    }
}
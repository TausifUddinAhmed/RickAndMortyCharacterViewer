package com.rickandmortycharacterviewer

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rickandmortycharacterviewer.fragment.detail.DetailFragment
import com.rickandmortycharacterviewer.fragment.characters.CharactersFragment
import com.rickandmortycharacterviewer.ui.theme.RickAndMortyCharacterViewerTheme
import com.rickandmortycharacterviewer.utils.Const.DETAIL_ARG_CHARACTER_ID
import com.rickandmortycharacterviewer.utils.Route
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

                    RickyAndMortyCharacterViewerAppScreen()

                    // Greeting("Android")
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
fun RickyAndMortyCharacterViewerAppScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Character.route,
    ) {
        composable(route = Route.Character.route) {
            CharactersFragment(
                onClickToDetailScreen = { gamesId ->
                    navController.navigate(
                        Route.Detail.createRoute(gamesId)
                    )
                }
            )
        }
        composable(
            route = Route.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARG_CHARACTER_ID){
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt(DETAIL_ARG_CHARACTER_ID)
            requireNotNull(characterId) { "characterId parameter wasn't found. Please make sure it's set!" }
            DetailFragment(id = characterId)
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun JetpackComposeAppScreenPreview() {
    RickAndMortyCharacterViewerTheme {
        RickyAndMortyCharacterViewerAppScreen()
    }
}




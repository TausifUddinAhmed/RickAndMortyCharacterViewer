package com.rickandmortycharacterviewer.repository_test

import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.utils.Response
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetCharacterDetailsUseCaseTest {



    @Test
    fun `Get character details by id, correct character name return`(): Unit = runBlocking {
        val fakeCharacterListRepository = FakeCharacterListRepository()
        val response = fakeCharacterListRepository.getCharacterDetails(2).first()
        val characterDetails: CharacterDetails

        when (response) {
            is Response.Loading -> {
                // Handle loading case if needed
                println("Loading...")
            }
            is Response.Success -> {
                // Access the data inside the Success case
                characterDetails = response.data!!
                if (characterDetails != null) {
                    System.out.println("characterDetails" + characterDetails)
                    Assert.assertEquals(characterDetails.name, "Morty Smith")
                } else {
                    println("Data is null")
                }
            }
            is Response.Failure -> {
                // Access the exception inside the Failure case
                val exception = response.e
                if (exception != null) {
                    println("Error: ${exception.message}")
                    // Handle the error here
                } else {
                    println("Error is null")
                }
            }
        }
    }

    @Test
    fun `Get character details by id, wrong character name return`(): Unit = runBlocking {
        val fakeCharacterListRepository = FakeCharacterListRepository()
        val response = fakeCharacterListRepository.getCharacterDetails(2).first()
        val characterDetails: CharacterDetails

        when (response) {
            is Response.Loading -> {
                // Handle loading case if needed
                println("Loading...")
            }
            is Response.Success -> {
                // Access the data inside the Success case
                characterDetails = response.data!!
                if (characterDetails != null) {
                    System.out.println("characterDetails" + characterDetails)
                    Assert.assertNotEquals(characterDetails.name, "Alien Rick")
                } else {
                    println("Data is null")
                }
            }
            is Response.Failure -> {
                // Access the exception inside the Failure case
                val exception = response.e
                if (exception != null) {
                    println("Error: ${exception.message}")
                    // Handle the error here
                } else {
                    println("Error is null")
                }
            }
        }
    }




  /*  @Composable
    @Test*/
   /* public fun `Get Movies List, correct movie list return` (): Unit = runBlocking{
        val success = fakemovieListRepository.getCharacterDetails(12).first()
        System.out.println("charactersDetails "+success.javaClass.name)
       // assertThat((charactersDetails.si.equals("title1"))).isTrue()
      //  assertThat((success.javaClass.name)).isTrue()
       // assertThat((charactersDetails.)).isTrue()
    }*/

    /*@Test
    fun `Get Movies List, incorrect movie list return` (): Unit = runBlocking{
        val movies = getMoviesUseCase("title").first()
        assertThat((movies.toData()?.results?.get(1)?.title.toString() == "title1")).isFalse()
    }*/
}
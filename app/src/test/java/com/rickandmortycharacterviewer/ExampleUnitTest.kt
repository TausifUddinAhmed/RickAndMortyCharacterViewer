package com.rickandmortycharacterviewer

import com.rickandmortycharacterviewer.repository_test.FakeCharacterListRepository
import com.rickandmortycharacterviewer.domain.model.CharacterDetails
import com.rickandmortycharacterviewer.utils.Response
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
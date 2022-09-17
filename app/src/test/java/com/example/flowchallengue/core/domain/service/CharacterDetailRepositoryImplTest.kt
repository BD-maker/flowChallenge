package com.example.flowchallengue.core.domain.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.flowchallengue.MainCoroutineRule
import com.example.flowchallengue.utils.Result
import com.example.flowchallengue.core.domain.model.CharacterModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

class CharacterDetailRepositoryImplTest {

    @MockK
    private lateinit var service: RickAndMortyAPI

    @MockK
    private lateinit var character: CharacterModel

    @MockK
    private lateinit var response: Response<CharacterModel>

    @MockK
    private lateinit var exception: Exception

    @MockK
    private lateinit var unknownHostException: UnknownHostException

    @MockK
    private lateinit var noSuchElementException: NoSuchElementException

    private lateinit var characterDetailRepository: CharacterDetailRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        characterDetailRepository = CharacterDetailRepositoryImpl(
            service,
            TestCoroutineDispatcher()
        )
    }

    @After
    fun clear() {
        unmockkAll()
    }

    @Test
    fun `when repository answer ok`() {
        coEvery { service.getCharacterDetail(FAKE_ID) } returns response
        coEvery { response.body() } returns character
        runBlockingTest {
            val result = characterDetailRepository.getCharacterDetail(FAKE_ID)
            assertEquals(result, Result.Success(character))
        }
    }

    @Test
    fun `when repository answer is null`() {
        coEvery { service.getCharacterDetail(FAKE_ID) } returns response
        coEvery { response.body() } returns null
        runBlockingTest {
            val result = characterDetailRepository.getCharacterDetail(FAKE_ID)
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `when repository fails because there isno internet connection`() {
        coEvery { service.getCharacterDetail(FAKE_ID) } throws unknownHostException
        runBlockingTest {
            val result = characterDetailRepository.getCharacterDetail(FAKE_ID)
            assertTrue(result == Result.Error(unknownHostException))
        }
    }

    @Test
    fun `when another exception occurs`() {
        coEvery { service.getCharacterDetail(FAKE_ID) } throws exception
        runBlockingTest {
            val result = characterDetailRepository.getCharacterDetail(FAKE_ID)
            assertEquals(result, Result.Error(exception))
        }
    }

    private companion object {
        const val FAKE_ID = "fake_id"
    }
}
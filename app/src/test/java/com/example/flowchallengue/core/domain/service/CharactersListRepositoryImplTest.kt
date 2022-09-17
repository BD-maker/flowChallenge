package com.example.flowchallengue.core.domain.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.flowchallengue.MainCoroutineRule
import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

class CharactersListRepositoryImplTest {

    @MockK
    private lateinit var service: RickAndMortyAPI

    @MockK
    private lateinit var characterListData: CharactersListData

    @MockK
    private lateinit var response: Response<CharactersListData>

    @MockK
    private lateinit var exception: Exception

    @MockK
    private lateinit var unknownHostException: UnknownHostException

    private lateinit var charactersListRepository: CharactersListRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        charactersListRepository = CharactersListRepositoryImpl(
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
        coEvery { service.getCharacters(FAKE_PAGE) } returns response
        coEvery { response.body() } returns characterListData
        runBlockingTest {
            val result = charactersListRepository.getCharacters(FAKE_PAGE)
            assertEquals(result, Result.Success(characterListData))
        }
    }

    @Test
    fun `when repository answer is null`() {
        coEvery { service.getCharacters(FAKE_PAGE) } returns response
        coEvery { response.body() } returns null
        runBlockingTest {
            val result = charactersListRepository.getCharacters(FAKE_PAGE)
            assertTrue(result is Result.Error)
        }
    }

    @Test
    fun `when repository fails because there isno internet connection`() {
        coEvery { service.getCharacters(FAKE_PAGE) } throws unknownHostException
        runBlockingTest {
            val result = charactersListRepository.getCharacters(FAKE_PAGE)
            assertTrue(result == Result.Error(unknownHostException))
        }
    }

    @Test
    fun `when another exception occurs`() {
        coEvery { service.getCharacters(FAKE_PAGE) } throws exception
        runBlockingTest {
            val result = charactersListRepository.getCharacters(FAKE_PAGE)
            assertEquals(result, Result.Error(exception))
        }
    }

    private companion object {
        const val FAKE_PAGE = "fake_page"
    }
}
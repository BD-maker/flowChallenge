package com.example.flowchallengue.core.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.flowchallengue.MainCoroutineRule
import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.core.domain.service.CharactersListRepository
import com.example.flowchallengue.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import junit.framework.Assert
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetCharactersUseCaseImplTest{
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @MockK
    private lateinit var repository: CharactersListRepository

    @MockK
    private lateinit var result: Result<CharactersListData>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        getCharactersUseCase = GetCharactersUseCaseImpl(repository)
    }

    @After
    fun clear() {
        unmockkAll()
    }

    @Test
    fun `when usecase returns character list`() {
        runBlockingTest {
            coEvery { repository.getCharacters(FAKE_PAGE) } returns result
            val result = getCharactersUseCase.getCharacters(FAKE_PAGE)
            Assert.assertTrue(result == result)
        }
    }

    private companion object {
        const val FAKE_PAGE = "fake_page"
    }
}
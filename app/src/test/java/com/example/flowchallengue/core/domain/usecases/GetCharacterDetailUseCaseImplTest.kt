package com.example.flowchallengue.core.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.flowchallengue.MainCoroutineRule
import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.core.domain.service.CharacterDetailRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Rule
import com.example.flowchallengue.utils.Result
import io.mockk.coEvery
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetCharacterDetailUseCaseImplTest {

    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @MockK
    private lateinit var repository: CharacterDetailRepository

    @MockK
    private lateinit var result: Result<CharacterModel>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        getCharacterDetailUseCase = GetCharacterDetailUseCaseImpl(repository)
    }

    @After
    fun clear() {
        unmockkAll()
    }

    @Test
    fun `when usecase returns character list`() {
        runBlockingTest {
            coEvery { repository.getCharacterDetail(FAKE_ID) } returns result
            val result = getCharacterDetailUseCase.getCharacter(FAKE_ID)
            assertTrue(result == result)
        }
    }

    private companion object {
        const val FAKE_ID = "fake_id"
    }

}
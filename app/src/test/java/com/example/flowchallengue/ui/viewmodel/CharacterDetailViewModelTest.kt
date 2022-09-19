package com.example.flowchallengue.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.flowchallengue.MainCoroutineRule
import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.core.domain.model.ViewState
import com.example.flowchallengue.core.domain.usecases.GetCharacterDetailUseCase
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.flowchallengue.utils.Result
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.*

class CharacterDetailViewModelTest {

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    @MockK
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @MockK
    private lateinit var characterModel: CharacterModel

    @MockK
    private lateinit var throwable: Throwable

    @RelaxedMockK
    private lateinit var observer: Observer<ViewState<Any>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        characterDetailViewModel = CharacterDetailViewModel(getCharacterDetailUseCase)
        characterDetailViewModel.viewState.observeForever(observer)
    }

    @After
    fun clear() {
        unmockkAll()
        characterDetailViewModel.viewState.removeObserver(observer)
    }

    @Test
    fun `when usecase response is Success`() {
        coEvery {
            getCharacterDetailUseCase.getCharacter(FAKE_ID)
        } returns Result.Success(characterModel)
        runBlockingTest {
            characterDetailViewModel.getCharacter(FAKE_ID)
            coVerify {
                observer.onChanged(
                    ViewState.Loading
                )
            }
            coVerify {
                observer.onChanged(
                    ViewState.Success(Result.Success(characterModel).data)
                )
            }
        }
    }

    @Test
    fun `when usecase response is Error`() {
        coEvery {
            getCharacterDetailUseCase.getCharacter(FAKE_ID)
        } returns Result.Error(throwable)
        runBlockingTest {
            characterDetailViewModel.getCharacter(FAKE_ID)
            coVerify {
                observer.onChanged(
                    ViewState.Error(Result.Error(throwable).exception)
                )
            }
        }
    }

    private companion object {
        const val FAKE_ID = "fake_id"
    }
}
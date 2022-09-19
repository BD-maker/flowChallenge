package com.example.flowchallengue.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.flowchallengue.MainCoroutineRule
import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.core.domain.model.ViewState
import com.example.flowchallengue.core.domain.usecases.GetCharactersUseCase
import com.example.flowchallengue.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersListViewModelTest {
    private lateinit var charactersListViewModel: CharactersListViewModel

    @MockK
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @MockK
    private lateinit var charactersListData: CharactersListData

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
        charactersListViewModel = CharactersListViewModel(getCharactersUseCase)
        charactersListViewModel.viewState.observeForever(observer)
    }

    @After
    fun clear() {
        unmockkAll()
        charactersListViewModel.viewState.removeObserver(observer)
    }

    @Test
    fun `when usecase response is Success`() {
        coEvery {
            getCharactersUseCase.getCharacters(FAKE_PAGE)
        } returns Result.Success(charactersListData)
        runBlockingTest {
            charactersListViewModel.getAllCharacters(FAKE_PAGE)
            coVerify {
                observer.onChanged(
                    ViewState.Loading
                )
            }
            coVerify {
                observer.onChanged(
                    ViewState.Success(Result.Success(charactersListData).data)
                )
            }
        }
    }

    @Test
    fun `when usecase response is Error`() {
        coEvery {
            getCharactersUseCase.getCharacters(FAKE_PAGE)
        } returns Result.Error(throwable)
        runBlockingTest {
            charactersListViewModel.getAllCharacters(FAKE_PAGE)
            coVerify {
                observer.onChanged(
                    ViewState.Error(Result.Error(throwable).exception)
                )
            }
        }
    }

    private companion object {
        const val FAKE_PAGE = "fake_page"
    }
}
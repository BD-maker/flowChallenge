package com.example.flowchallengue.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowchallengue.core.domain.model.ViewState
import com.example.flowchallengue.core.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.launch
import com.example.flowchallengue.utils.Result

class CharactersListViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<Any>>()
    val viewState: LiveData<ViewState<Any>> = _viewState

    fun getAllCharacters(page: String) {
        viewModelScope.launch {
            _viewState.postValue(ViewState.Loading)
            when (val response = getCharactersUseCase.getCharacters(page)) {
                is Result.Success -> _viewState.postValue(ViewState.Success(response.data))
                is Result.Error -> _viewState.postValue(ViewState.Error(response.exception))
            }
        }
    }


}
package com.example.flowchallengue.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowchallengue.core.domain.model.ViewState
import com.example.flowchallengue.core.domain.usecases.GetCharacterDetailUseCase
import com.example.flowchallengue.utils.Result
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<Any>>()
    val viewState: LiveData<ViewState<Any>> = _viewState

    fun getCharacter(id: String) {
        viewModelScope.launch {
            _viewState.postValue(ViewState.Loading)
            when (val response = getCharacterDetailUseCase.getCharacter(id)) {
                is Result.Success -> _viewState.postValue(ViewState.Success(response.data))
                is Result.Error -> _viewState.postValue(ViewState.Error(response.exception))
            }
        }
    }

}
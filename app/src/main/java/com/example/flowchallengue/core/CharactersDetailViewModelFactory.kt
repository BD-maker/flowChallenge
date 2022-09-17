package com.example.flowchallengue.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flowchallengue.core.domain.usecases.GetCharacterDetailUseCase
import com.example.flowchallengue.ui.viewmodel.CharacterDetailViewModel

class CharactersDetailViewModelFactory(
    private val useCase: GetCharacterDetailUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(useCase) as T
    }
}
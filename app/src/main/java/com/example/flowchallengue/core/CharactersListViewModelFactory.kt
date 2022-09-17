package com.example.flowchallengue.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flowchallengue.core.domain.usecases.GetCharactersUseCase
import com.example.flowchallengue.ui.viewmodel.CharactersListViewModel

class CharactersListViewModelFactory(
    private val useCase: GetCharactersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersListViewModel(useCase) as T
    }
}
package com.example.flowchallengue.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.flowchallengue.core.domain.usecases.GetCharacterDetailUseCase

class CharacterDetailViewModel(
    val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

}
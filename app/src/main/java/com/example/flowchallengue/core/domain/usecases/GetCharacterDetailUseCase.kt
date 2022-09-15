package com.example.flowchallengue.core.domain.usecases

import com.example.flowchallengue.core.domain.model.CharactersListData

interface GetCharacterDetailUseCase {
    fun getCharacters() : CharactersListData
}
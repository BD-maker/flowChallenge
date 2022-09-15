package com.example.flowchallengue.core.domain.usecases

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.core.domain.service.CharacterDetailRepository

class GetCharacterDetailUseCaseImpl(
    val repository : CharacterDetailRepository
) : GetCharacterDetailUseCase{

    override fun getCharacters(): CharactersListData {
        TODO("Not yet implemented")
    }
}
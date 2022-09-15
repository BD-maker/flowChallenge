package com.example.flowchallengue.core.domain.usecases

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.core.domain.service.CharactersRepository

class GetCharactersUseCaseImpl(
    val repository : CharactersRepository
) : GetCharactersUseCase {

    override fun getCharacters(): CharactersListData {
        TODO("Not yet implemented")
    }
}
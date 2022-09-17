package com.example.flowchallengue.core.domain.usecases

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.core.domain.service.CharactersListRepository
import com.example.flowchallengue.utils.Result

interface GetCharactersUseCase {
    suspend fun getCharacters(page : String) : Result<CharactersListData>

    companion object Factory{
        fun create() : GetCharactersUseCase{
            return GetCharactersUseCaseImpl(
                CharactersListRepository.create()
            )
        }
    }
}
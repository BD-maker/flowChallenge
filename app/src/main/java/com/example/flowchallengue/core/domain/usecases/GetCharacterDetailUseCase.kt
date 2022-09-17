package com.example.flowchallengue.core.domain.usecases

import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.core.domain.service.CharacterDetailRepository
import com.example.flowchallengue.utils.Result

interface GetCharacterDetailUseCase {
    suspend fun getCharacter(id: String): Result<CharacterModel>

    companion object Factory {
        fun create(): GetCharacterDetailUseCase {
            return GetCharacterDetailUseCaseImpl(
                CharacterDetailRepository.create()
            )
        }
    }
}
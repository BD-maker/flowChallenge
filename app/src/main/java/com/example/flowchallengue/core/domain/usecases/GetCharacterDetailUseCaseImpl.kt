package com.example.flowchallengue.core.domain.usecases

import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.core.domain.service.CharacterDetailRepository
import com.example.flowchallengue.utils.Result

class GetCharacterDetailUseCaseImpl(
    val repository: CharacterDetailRepository
) : GetCharacterDetailUseCase {

    override suspend fun getCharacter(id: String): Result<CharacterModel> {
        return repository.getCharacterDetail(id)
    }
}
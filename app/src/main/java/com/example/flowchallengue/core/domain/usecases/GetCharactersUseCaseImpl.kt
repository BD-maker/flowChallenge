package com.example.flowchallengue.core.domain.usecases

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.core.domain.service.CharactersListRepository
import com.example.flowchallengue.utils.Result

class GetCharactersUseCaseImpl(
    private val listRepository : CharactersListRepository
) : GetCharactersUseCase {

    override suspend fun getCharacters(page: String): Result<CharactersListData> {
        return listRepository.getCharacters(page)
    }
}
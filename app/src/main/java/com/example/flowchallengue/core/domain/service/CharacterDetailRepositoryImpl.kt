package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.utils.Resource

class CharacterDetailRepositoryImpl : CharacterDetailRepository {

    override suspend fun getCharacterDetail(): Resource<CharactersListData> {
        TODO("Not yet implemented")
    }
}
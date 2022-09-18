package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.utils.Result

interface CharacterDetailRepository {
    suspend fun getCharacterDetail(id: String): Result<CharacterModel>

    companion object Factory {
        fun create(): CharacterDetailRepository {
            val service = ServiceFactory.create(RickAndMortyAPI::class.java)
            return CharacterDetailRepositoryImpl(service)
        }
    }
}
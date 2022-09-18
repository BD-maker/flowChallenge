package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.utils.Result

interface CharactersListRepository {
    suspend fun getCharacters(page: String): Result<CharactersListData>

    companion object Factory {
        fun create(): CharactersListRepository {
            val service = ServiceFactory.create(RickAndMortyAPI::class.java)
            return CharactersListRepositoryImpl(service)
        }
    }
}
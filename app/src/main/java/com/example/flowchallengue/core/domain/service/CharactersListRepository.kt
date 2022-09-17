package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.utils.AppConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.flowchallengue.utils.Result

interface CharactersListRepository {
    suspend fun getCharacters(page: String): Result<CharactersListData>

    companion object Factory {
        fun create(): CharactersListRepository {
            val service = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RickAndMortyAPI::class.java)
            return CharactersListListRepositoryImpl(service)
        }
    }
}
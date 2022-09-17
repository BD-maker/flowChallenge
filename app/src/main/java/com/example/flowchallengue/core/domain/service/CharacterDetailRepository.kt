package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.utils.AppConfig
import com.example.flowchallengue.utils.Result
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CharacterDetailRepository {
    suspend fun getCharacterDetail(id: String): Result<CharacterModel>

    companion object Factory {
        fun create(): CharacterDetailRepository {
            val service = Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RickAndMortyAPI::class.java)
            return CharacterDetailRepositoryImpl(service)
        }
    }
}
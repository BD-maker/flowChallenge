package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.core.domain.model.CharactersListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyAPI {

    @GET("character")
    suspend fun getCharacters() : Response<CharactersListData>

    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") itemId: String) : Response<CharacterModel>

}
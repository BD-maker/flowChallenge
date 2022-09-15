package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.utils.Resource

class CharactersRepositoryImpl(
    val service : RickAndMortyAPI
) : CharactersRepository{

    override suspend fun getCharacters(): Resource<CharactersListData> {
        return try {
            val response = service.getCharacters()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error Desconocido", null)
            } else {
                Resource.error("Error Desconocido", null)
            }
        } catch (e: Exception) {
            Resource.error("No hay conexión a Internet", null)
        }
    }
}
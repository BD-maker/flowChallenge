package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

class CharacterDetailRepositoryImpl(
    private val service: RickAndMortyAPI
) : CharacterDetailRepository {

    override suspend fun getCharacterDetail(id: String): Result<CharacterModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getCharacterDetail(id)
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Error(NoSuchElementException())
            } catch (e: UnknownHostException) {
                Result.Error(e)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}
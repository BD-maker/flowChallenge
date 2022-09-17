package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.UnknownHostException

class CharacterDetailRepositoryImpl(
    private val service: RickAndMortyAPI,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharacterDetailRepository {

    override suspend fun getCharacterDetail(id: String): Result<CharacterModel> {
        return withContext(dispatcher) {
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
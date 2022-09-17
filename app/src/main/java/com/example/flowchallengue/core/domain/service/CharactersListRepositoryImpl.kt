package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.UnknownHostException

class CharactersListRepositoryImpl(
    private val service: RickAndMortyAPI,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharactersListRepository {

    override suspend fun getCharacters(page : String): Result<CharactersListData> {
        return withContext(dispatcher) {
            try {
                val response = service.getCharacters(page)
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Error(Exception())
            } catch (e: UnknownHostException) {
                Result.Error(e)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}
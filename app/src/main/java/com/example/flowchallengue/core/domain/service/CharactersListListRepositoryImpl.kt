package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

class CharactersListListRepositoryImpl(
    private val service: RickAndMortyAPI
) : CharactersListRepository {

    override suspend fun getCharacters(): Result<CharactersListData> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getCharacters()
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
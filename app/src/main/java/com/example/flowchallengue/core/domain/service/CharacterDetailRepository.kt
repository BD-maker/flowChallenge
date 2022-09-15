package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.core.domain.model.CharactersListData
import com.example.flowchallengue.utils.Resource

interface CharacterDetailRepository {
    suspend fun getCharacterDetail() : Resource<CharactersListData>
}
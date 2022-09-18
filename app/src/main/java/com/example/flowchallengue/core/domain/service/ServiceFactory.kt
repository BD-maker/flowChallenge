package com.example.flowchallengue.core.domain.service

import com.example.flowchallengue.utils.AppConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {

    fun <T> create(service: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service) as T
    }
}
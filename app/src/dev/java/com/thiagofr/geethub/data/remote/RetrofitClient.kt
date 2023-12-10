package com.thiagofr.geethub.data.remote

import com.thiagofr.geethub.data.remote.api.GitHubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.github.com"

    fun create(): GitHubService {

        return Retrofit.Builder()
            .apply {
                baseUrl(BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
            }.build()
            .create(GitHubService::class.java)
    }
}

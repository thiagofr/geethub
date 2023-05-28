package com.thiagofr.geethub.data.remote

import com.thiagofr.geethub.data.remote.api.GitHubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.github.com"

    fun create(): GitHubService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(GitHubService::class.java)
    }
}

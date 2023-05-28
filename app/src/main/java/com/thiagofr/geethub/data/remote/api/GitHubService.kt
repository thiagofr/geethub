package com.thiagofr.geethub.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("/users")
    suspend fun getUsersAsync(): List<UserResponse>

    @GET("/users/{user}")
    suspend fun getUser(@Path("user")login: String)
}

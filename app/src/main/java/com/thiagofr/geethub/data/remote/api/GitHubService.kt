package com.thiagofr.geethub.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface GitHubService {
    @GET("/users")
    fun getUsers(): Call<List<UserResponse>>

    @GET("/users/{user}")
    fun getUser(@Path("user") login: String): Call<UserResponse>

    @GET("/users/{user}/repos")
    fun getRepositoryList(@Path("user") login: String): Call<List<RepositoryResponse>>
}

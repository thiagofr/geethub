package com.thiagofr.geethub.domain.repository

import com.thiagofr.geethub.data.remote.api.RepositoryResponse
import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.data.remote.api.UserResponse

interface UserRepository {
    suspend fun getUserList(): Response<List<UserResponse>>

    suspend fun getUser(login: String): Response<UserResponse>

    suspend fun getRepositoryListByUser(login: String): Response<List<RepositoryResponse>>

}
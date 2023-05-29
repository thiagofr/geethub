package com.thiagofr.geethub.domain.repository

import com.thiagofr.geethub.data.remote.api.GitHubService
import com.thiagofr.geethub.data.remote.api.RepositoryResponse
import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.data.remote.api.UserResponse
import com.thiagofr.geethub.util.getResult

class UserRemoteRepository(
    private val api: GitHubService
) : UserRepository {
    override suspend fun getUserList(): Response<List<UserResponse>> {
        return api.getUsers().getResult()
    }

    override suspend fun getUser(login: String): Response<UserResponse> {
        return api.getUser(login).getResult()
    }

    override suspend fun getRepositoryListByUser(login: String): Response<List<RepositoryResponse>> {
        return api.getRepositoryList(login).getResult()
    }
}
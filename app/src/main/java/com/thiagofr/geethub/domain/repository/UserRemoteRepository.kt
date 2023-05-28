package com.thiagofr.geethub.domain.repository

import com.thiagofr.geethub.data.remote.api.GitHubService
import com.thiagofr.geethub.domain.mapper.UserMapper
import com.thiagofr.geethub.domain.model.User

class UserRemoteRepository(
    private val api: GitHubService,
    private val mapper: UserMapper
) : UserRepository {
    override suspend fun getUserList(): List<User> {
        val response = api.getUsersAsync()
        return response.map { mapper.map(it) }
    }
}
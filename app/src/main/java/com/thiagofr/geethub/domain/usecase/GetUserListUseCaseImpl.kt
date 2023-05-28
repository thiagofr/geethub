package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.domain.mapper.UserMapper
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.domain.repository.UserRepository

class GetUserListUseCaseImpl(
    private val repository: UserRepository,
    private val mapper: UserMapper
) : GetUserListUseCase {
    override suspend fun invoke(): Result<List<User>> {
        val response = repository.getUserList()
        return when (response) {
            is Response.Success ->
                Result.Success(
                    response.data.map {
                        mapper.map(it)
                    }
                )
            is Response.Error ->
                Result.Error(response.exception)
        }
    }
}

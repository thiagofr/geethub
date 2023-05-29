package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.data.remote.api.UserResponse
import com.thiagofr.geethub.domain.mapper.UserMapperImpl
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.domain.repository.UserRepository

class GetUserUserCaseImpl(
    private val repository: UserRepository,
    private val mapper: UserMapperImpl,
    private val getRepositoryListByUserUseCase: GetRepositoryListByUserUseCase
) : GetUserUserCase {
    override suspend fun invoke(login: String): Result<User> {
        return when (val response = repository.getUser(login)) {
            is Response.Success -> {
                return handleSuccess(login, response)
            }
            is Response.Error ->
                Result.Error(response.exception)
        }
    }

    private suspend fun handleSuccess(
        login: String,
        response: Response.Success<UserResponse>
    ): Result<User> {
        return when (val repositoryListResponse = getRepositoryListByUserUseCase(login)) {
            is Result.Success -> {
                val repositoryList = repositoryListResponse.data
                val user = mapper.map(response.data).copy(
                    repositoryList = repositoryList
                )
                Result.Success(
                    user
                )
            }
            is Result.Error -> Result.Error(repositoryListResponse.exception)
        }
    }
}

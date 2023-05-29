package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.domain.mapper.RepositoryMapperImpl
import com.thiagofr.geethub.domain.model.Repository
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.repository.UserRepository

class GetRepositoryListByUserUseCaseImpl(
    val repository: UserRepository,
    val mapper: RepositoryMapperImpl
) : GetRepositoryListByUserUseCase {
    override suspend fun invoke(login: String): Result<List<Repository>> {

        return when (val response = repository.getRepositoryListByUser(login)) {
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
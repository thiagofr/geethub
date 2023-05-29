package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.domain.model.Repository
import com.thiagofr.geethub.domain.model.Result

interface GetRepositoryListByUserUseCase {
    suspend operator fun invoke(login: String): Result<List<Repository>>
}
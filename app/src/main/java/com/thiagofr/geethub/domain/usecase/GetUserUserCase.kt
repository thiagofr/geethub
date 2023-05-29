package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.model.User

interface GetUserUserCase {
    suspend operator fun invoke(login: String): Result<User>
}

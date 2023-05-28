package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.domain.model.User

interface GetUserListUseCase {
    suspend operator fun invoke(): List<User>
}

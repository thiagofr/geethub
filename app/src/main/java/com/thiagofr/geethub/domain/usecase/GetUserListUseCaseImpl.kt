package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.domain.repository.UserRepository

class GetUserListUseCaseImpl(
    private val repository: UserRepository
): GetUserListUseCase {
    override suspend fun invoke(): List<User> {
        return repository.getUserList()
    }
}
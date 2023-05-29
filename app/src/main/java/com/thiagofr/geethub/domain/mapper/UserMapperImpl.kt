package com.thiagofr.geethub.domain.mapper

import com.thiagofr.geethub.data.remote.api.UserResponse
import com.thiagofr.geethub.domain.model.User

class UserMapperImpl : UserMapper<UserResponse, User> {
    override fun map(from: UserResponse): User {
        return User(
            id = from.id,
            login = from.login,
            type = from.type,
            avatarUrl = from.avatarUrl,
            followers = from.followers,
            following = from.following,
            location = from.location,
            name = from.name,
            repositoryList = emptyList()
        )
    }
}

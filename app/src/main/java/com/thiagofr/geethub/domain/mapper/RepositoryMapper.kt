package com.thiagofr.geethub.domain.mapper

import com.thiagofr.geethub.data.remote.api.RepositoryResponse
import com.thiagofr.geethub.data.remote.api.UserResponse
import com.thiagofr.geethub.domain.Mapper
import com.thiagofr.geethub.domain.model.Repository
import com.thiagofr.geethub.domain.model.User

class RepositoryMapper : Mapper<RepositoryResponse, Repository> {
    override fun map(from: RepositoryResponse): Repository {
        return Repository(
            name = from.name,
            fullName = from.fullName,
            isPrivate = from.isPrivate,
        )
    }
}

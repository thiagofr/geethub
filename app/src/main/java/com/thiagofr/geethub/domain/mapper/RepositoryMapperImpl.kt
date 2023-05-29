package com.thiagofr.geethub.domain.mapper

import com.thiagofr.geethub.data.remote.api.RepositoryResponse
import com.thiagofr.geethub.domain.model.Repository

class RepositoryMapperImpl : RepositoryMapper<RepositoryResponse, Repository> {
    override fun map(from: RepositoryResponse): Repository {
        return Repository(
            name = from.name,
            fullName = from.fullName,
            isPrivate = from.isPrivate,
        )
    }
}

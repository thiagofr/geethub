package com.thiagofr.geethub.util

import com.thiagofr.geethub.data.remote.api.RepositoryResponse

object RepositoryResponseUtil {
    fun getRepositoryResponse(
        name: String = DataUtils.getStringRandom(),
        fullName: String = DataUtils.getStringRandom(),
        isPrivate: Boolean = DataUtils.getBooleanRandom()
    ) = RepositoryResponse(
        name = name,
        fullName = fullName,
        isPrivate = isPrivate
    )

    fun getRepositoryResponseList() =
        Array(10) { getRepositoryResponse() }.toList()
}
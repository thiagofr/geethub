package com.thiagofr.geethub.util

import com.thiagofr.geethub.domain.model.Repository

object RepositoryUtil {
    fun getRepository(
        name: String = DataUtils.getStringRandom(),
        fullName: String = DataUtils.getStringRandom(),
        isPrivate: Boolean = DataUtils.getBooleanRandom()
    ) = Repository(
        name = name,
        fullName = fullName,
        isPrivate = isPrivate
    )

    fun getRepositoryList() =
        Array(10) { getRepository() }.toList()
}
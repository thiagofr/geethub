package com.thiagofr.geethub.util

import com.thiagofr.geethub.domain.model.Repository
import com.thiagofr.geethub.domain.model.User

object UserUtil {
    fun getUser(
        id: Int = DataUtils.getIntRandom(),
        login: String = DataUtils.getStringRandom(),
        avatarUrl: String = DataUtils.getStringRandom(),
        type: String = DataUtils.getStringRandom(),
        followers: Int = DataUtils.getIntRandom(),
        following: Int = DataUtils.getIntRandom(),
        location: String = DataUtils.getStringRandom(),
        name: String = DataUtils.getStringRandom(),
        repositoryList: List<Repository>? = null
    ) = User(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        type = type,
        followers = followers,
        following = following,
        location = location,
        name = name,
        repositoryList = repositoryList
    )

    fun getUserList() = Array(10) { getUser() }.toList()
}
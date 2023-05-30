package com.thiagofr.geethub.util

import com.thiagofr.geethub.data.remote.api.UserResponse

object UserResponseUtil {
    fun getUserResponse(
        id: Int = DataUtils.getIntRandom(),
        login: String = DataUtils.getStringRandom(),
        avatarUrl: String = DataUtils.getStringRandom(),
        type: String = DataUtils.getStringRandom(),
        followers: Int = DataUtils.getIntRandom(),
        following: Int = DataUtils.getIntRandom(),
        location: String = DataUtils.getStringRandom(),
        name: String = DataUtils.getStringRandom(),
    ) = UserResponse(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        type = type,
        followers = followers,
        following = following,
        location = location,
        name = name,
    )

    fun getUserResponseList() = Array(10) { getUserResponse() }.toList()
}
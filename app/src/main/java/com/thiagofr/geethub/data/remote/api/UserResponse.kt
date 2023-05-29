package com.thiagofr.geethub.data.remote.api

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val type: String,
    val followers: Int?,
    val following: Int?,
    val location: String?,
    val name: String?,
)

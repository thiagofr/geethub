package com.thiagofr.geethub.domain.model

data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val type: String,
    val followers: Int?,
    val following: Int?,
    val location: String?,
    val name: String?,
    val repositoryList: List<Repository>
)

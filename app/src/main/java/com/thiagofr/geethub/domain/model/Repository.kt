package com.thiagofr.geethub.domain.model

data class Repository(
    val name: String,
    val fullName: String,
    val isPrivate: Boolean
)

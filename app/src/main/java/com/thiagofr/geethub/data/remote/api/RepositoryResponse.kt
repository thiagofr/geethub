package com.thiagofr.geethub.data.remote.api

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("private")
    val isPrivate: Boolean
)
package com.thiagofr.geethub.domain.repository

import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.data.remote.api.UserResponse

interface UserRepository {
    suspend fun getUserList(): Response<List<UserResponse>>

}
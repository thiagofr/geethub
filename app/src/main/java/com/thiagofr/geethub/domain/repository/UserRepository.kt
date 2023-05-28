package com.thiagofr.geethub.domain.repository

import com.thiagofr.geethub.domain.model.User

interface UserRepository {

    suspend fun getUserList(): List<User>

}
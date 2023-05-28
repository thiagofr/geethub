package com.thiagofr.geethub.di

import com.thiagofr.geethub.data.remote.api.UserResponse
import com.thiagofr.geethub.domain.Mapper
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.domain.mapper.UserMapper
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mappersModule = module {
    singleOf(::UserMapper) {
        bind<Mapper<UserResponse, User>>()
    }
}

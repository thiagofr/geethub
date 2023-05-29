package com.thiagofr.geethub.di

import com.thiagofr.geethub.data.remote.api.RepositoryResponse
import com.thiagofr.geethub.data.remote.api.UserResponse
import com.thiagofr.geethub.domain.mapper.*
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.domain.model.Repository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mappersModule = module {
    singleOf(::UserMapperImpl) {
        bind<UserMapper<UserResponse, User>>()
    }
    singleOf(::RepositoryMapperImpl) {
        bind<RepositoryMapper<RepositoryResponse, Repository>>()
    }
}

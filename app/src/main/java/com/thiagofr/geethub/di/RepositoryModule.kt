package com.thiagofr.geethub.di

import com.thiagofr.geethub.domain.repository.UserRemoteRepository
import com.thiagofr.geethub.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository>{
        UserRemoteRepository(
            api = get(),
            mapper = get(),
        )
    }
}

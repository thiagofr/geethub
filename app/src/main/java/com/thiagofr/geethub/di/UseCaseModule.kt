package com.thiagofr.geethub.di

import com.thiagofr.geethub.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetUserListUseCase> {
        GetUserListUseCaseImpl(
            repository = get(),
            mapper = get(),
        )
    }
    factory<GetUserUserCase> {
        GetUserUserCaseImpl(
            repository = get(),
            mapper = get(),
            getRepositoryListByUserUseCase = get()
        )
    }
    factory<GetRepositoryListByUserUseCase> {
        GetRepositoryListByUserUseCaseImpl(
            repository = get(),
            mapper = get(),
        )
    }
}
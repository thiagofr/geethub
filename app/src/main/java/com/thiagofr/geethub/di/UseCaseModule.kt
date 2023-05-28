package com.thiagofr.geethub.di

import com.thiagofr.geethub.domain.usecase.GetUserListUseCase
import com.thiagofr.geethub.domain.usecase.GetUserListUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetUserListUseCase>{
        GetUserListUseCaseImpl(
            repository = get(),
            mapper = get(),
        )
    }
}
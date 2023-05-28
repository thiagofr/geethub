package com.thiagofr.geethub.di

import com.thiagofr.geethub.data.remote.RetrofitClient
import com.thiagofr.geethub.data.remote.api.GitHubService
import org.koin.dsl.module

val networkModule = module {
    single<GitHubService> {
        RetrofitClient.create()
    }
}

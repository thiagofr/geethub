package com.thiagofr.geethub.di

import com.thiagofr.geethub.presenter.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(
            getUserListUseCase = get()
        )
    }
}

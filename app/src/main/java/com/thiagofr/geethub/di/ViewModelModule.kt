package com.thiagofr.geethub.di

import com.thiagofr.geethub.presenter.user.UserViewModel
import com.thiagofr.geethub.presenter.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        UserListViewModel(
            getUserListUseCase = get()
        )
    }

    viewModel {
        UserViewModel(
            getUserUserCase = get()
        )
    }
}

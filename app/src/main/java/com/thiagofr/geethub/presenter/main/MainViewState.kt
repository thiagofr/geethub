package com.thiagofr.geethub.presenter.main

import com.thiagofr.geethub.domain.model.User

sealed class MainViewState {
    class Loading(val isLoading: Boolean): MainViewState()
    object Error: MainViewState()
    class SetUserList(val userList: List<User>): MainViewState()
}

package com.thiagofr.geethub.presenter.user

import com.thiagofr.geethub.domain.model.User

sealed class UserViewState {
    class Loading(val isLoading: Boolean): UserViewState()
    class SetUserInfo(val data: User) : UserViewState()
    object Error : UserViewState()
}
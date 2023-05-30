package com.thiagofr.geethub.presenter.user

import com.thiagofr.geethub.domain.model.User

sealed class UserViewState {
    object Loading: UserViewState()
    object SetUserInfo: UserViewState() {
        var data: User? = null
    }
    object Error : UserViewState()
}
package com.thiagofr.geethub.presenter.user

sealed class UserViewAction {
    class Init(val login: String) : UserViewAction()
}
package com.thiagofr.geethub.presenter.main

sealed class MainViewAction {
    object GetUserList: MainViewAction()
}
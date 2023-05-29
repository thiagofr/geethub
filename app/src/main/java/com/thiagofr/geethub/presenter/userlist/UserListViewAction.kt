package com.thiagofr.geethub.presenter.userlist

sealed class UserListViewAction {
    object GetUserList: UserListViewAction()
}
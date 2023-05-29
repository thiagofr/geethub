package com.thiagofr.geethub.presenter.userlist

import com.thiagofr.geethub.domain.model.User

sealed class UserListViewState {
    class Loading(val isLoading: Boolean): UserListViewState()
    object Error: UserListViewState()
    class SetUserList(val userList: List<User>): UserListViewState()
}

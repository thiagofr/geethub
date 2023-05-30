package com.thiagofr.geethub.presenter.userlist

import com.thiagofr.geethub.domain.model.User

sealed class UserListViewState {
    object Loading: UserListViewState()
    object Error: UserListViewState()
    object SetUserList: UserListViewState() {
        var userList: List<User>? = null
    }
}

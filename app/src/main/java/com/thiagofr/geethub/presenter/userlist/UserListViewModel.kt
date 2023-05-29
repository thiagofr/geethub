package com.thiagofr.geethub.presenter.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.usecase.GetUserListUseCase
import com.thiagofr.geethub.presenter.user.UserViewAction
import com.thiagofr.geethub.util.launch
import com.thiagofr.geethub.presenter.userlist.UserListViewState as ViewState

class UserListViewModel(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> get() = _viewState

    fun dispatchAction(action: UserListViewAction) {
        when (action) {
            UserListViewAction.GetUserList -> getUsers()
        }
    }

    private fun getUsers() = launch {
        _viewState.postValue(ViewState.Loading(isLoading = true))
        when (val result = getUserListUseCase()) {
            is Result.Success -> {
                _viewState.postValue(
                    ViewState.SetUserList(result.data)
                )
            }
            is Result.Error -> {
                _viewState.postValue(ViewState.Error)
            }
        }
    }
}

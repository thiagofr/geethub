package com.thiagofr.geethub.presenter.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.usecase.GetUserUserCase
import com.thiagofr.geethub.util.launch
import com.thiagofr.geethub.presenter.user.UserViewAction as ViewAction
import com.thiagofr.geethub.presenter.user.UserViewState as ViewState

class UserViewModel(
    private val getUserUserCase: GetUserUserCase
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> get() = _viewState

    fun dispatchAction(action: ViewAction) {
        when (action) {
            is ViewAction.Init -> handleInit(action.login)
        }
    }

    private fun handleInit(login: String) = launch {
        _viewState.postValue(ViewState.Loading(isLoading = true))

        when (val result = getUserUserCase(login)) {
            is Result.Success -> {
                _viewState.postValue(
                    ViewState.SetUserInfo(result.data)
                )
            }
            is Result.Error -> {
                _viewState.postValue(ViewState.Error)
            }
        }

    }

}
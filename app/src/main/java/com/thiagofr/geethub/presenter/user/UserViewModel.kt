package com.thiagofr.geethub.presenter.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.usecase.GetUserUserCase
import com.thiagofr.geethub.util.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.thiagofr.geethub.presenter.user.UserViewAction as ViewAction
import com.thiagofr.geethub.presenter.user.UserViewState as ViewState

class UserViewModel(
    private val getUserUserCase: GetUserUserCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Init)
    val viewState: Flow<ViewState> = _viewState

    fun dispatchAction(action: ViewAction) {
        when (action) {
            is ViewAction.Init -> handleInit(action.login)
        }
    }

    private fun handleInit(login: String) = viewModelScope.launch(Dispatchers.IO) {
        _viewState.emit(ViewState.Loading)
        when (val result = getUserUserCase(login)) {
            is Result.Success -> {
                val setUserInfo = ViewState.SetUserInfo
                setUserInfo.data = result.data

                _viewState.emit(setUserInfo)
            }
            is Result.Error -> {
                _viewState.emit(ViewState.Error)
            }
        }
    }
}
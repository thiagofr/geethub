package com.thiagofr.geethub.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUserListUseCase: GetUserListUseCase
): ViewModel() {

    val state = MutableLiveData<List<User>>()

    fun getUsers() = viewModelScope.launch {
        val users = getUserListUseCase()
        state.postValue(users)
    }

}

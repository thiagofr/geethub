package com.thiagofr.geethub.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.domain.usecase.GetUserListUseCase
import com.thiagofr.geethub.util.launch

class MainViewModel(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    val state = MutableLiveData<List<User>>()

    fun getUsers() = launch {
        when (val result = getUserListUseCase()) {
            is Result.Success -> state.postValue(result.data)
            is Result.Error -> state.postValue(emptyList())
        }
    }
}

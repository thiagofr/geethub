package com.thiagofr.geethub.presenter.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.usecase.GetUserUserCase
import com.thiagofr.geethub.util.UserUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    private lateinit var viewModel: UserViewModel

    @Mock
    private lateinit var getUserUserCase: GetUserUserCase

    @Mock
    private lateinit var observer: Observer<UserViewState>

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = UserViewModel(
            getUserUserCase = getUserUserCase
        )
    }

    @Test
    fun `when viewAction is Init then ViewState must be Loading`() = runBlocking {
        val latch = CountDownLatch(1)

        val user = UserUtil.getUser()

        viewModel.viewState.observeForever(observer)

        `when`(getUserUserCase(LOGIN)).thenReturn(Result.Success(user))

        viewModel.dispatchAction(UserViewAction.Init(LOGIN))

        withContext(Dispatchers.IO) {
            latch.await(5, TimeUnit.MILLISECONDS)
        }

        verify(observer, times(1)).onChanged(UserViewState.Loading)
        verify(observer, times(1)).onChanged(UserViewState.SetUserInfo)

        viewModel.viewState.removeObserver(observer)
    }

    @Test
    fun `when viewAction is Init then ViewState must be Error`() = runBlocking {
        val latch = CountDownLatch(1)

        viewModel.viewState.observeForever(observer)

        `when`(getUserUserCase(LOGIN)).thenReturn(Result.Error(Exception("No data found")))

        viewModel.dispatchAction(UserViewAction.Init(LOGIN))

        withContext(Dispatchers.IO) {
            latch.await(5, TimeUnit.MILLISECONDS)
        }

        verify(observer, times(1)).onChanged(UserViewState.Loading)
        verify(observer, times(1)).onChanged(UserViewState.Error)

        viewModel.viewState.removeObserver(observer)
    }

    companion object {
        private const val LOGIN = "login"
    }
}

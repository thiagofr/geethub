package com.thiagofr.geethub.presenter.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.usecase.GetUserListUseCase
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

    private lateinit var viewModel: UserListViewModel

    @Mock
    private lateinit var getUserListUseCase: GetUserListUseCase

    @Mock
    private lateinit var observer: Observer<UserListViewState>

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = UserListViewModel(
            getUserListUseCase = getUserListUseCase
        )
    }

    @Test
    fun `when viewAction is Init then ViewState must be Loading`() = runBlocking {
        val latch = CountDownLatch(1)

        val list = UserUtil.getUserList()

        viewModel.viewState.observeForever(observer)

        `when`(getUserListUseCase()).thenReturn(Result.Success(list))

        viewModel.dispatchAction(UserListViewAction.GetUserList)

        withContext(Dispatchers.IO) {
            latch.await(5, TimeUnit.MILLISECONDS)
        }

        verify(observer, times(1)).onChanged(UserListViewState.Loading)
        verify(observer, times(1)).onChanged(UserListViewState.SetUserList)

        viewModel.viewState.removeObserver(observer)
    }

    @Test
    fun `when viewAction is Init then ViewState must be Error`() = runBlocking {
        val latch = CountDownLatch(1)

        viewModel.viewState.observeForever(observer)

        `when`(getUserListUseCase()).thenReturn(Result.Error(Exception("No data found")))

        viewModel.dispatchAction(UserListViewAction.GetUserList)

        withContext(Dispatchers.IO) {
            latch.await(5, TimeUnit.MILLISECONDS)
        }

        verify(observer, times(1)).onChanged(UserListViewState.Loading)
        verify(observer, times(1)).onChanged(UserListViewState.Error)

        viewModel.viewState.removeObserver(observer)
    }
}

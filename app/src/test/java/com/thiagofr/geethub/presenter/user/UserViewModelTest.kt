package com.thiagofr.geethub.presenter.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.usecase.GetUserUserCase
import com.thiagofr.geethub.util.UserUtil
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import com.thiagofr.geethub.presenter.user.UserViewState as ViewState

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    private lateinit var viewModel: UserViewModel

    @Mock
    private lateinit var getUserUserCase: GetUserUserCase

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
    fun `when viewAction is Init then ViewState must be Loading`() = runTest {
        viewModel.dispatchAction(UserViewAction.Init(LOGIN))

        val viewState = viewModel.viewState.take(1).first()

        assertTrue(viewState is ViewState.Loading)
    }

    @Test
    fun `when viewAction is Init then ViewState must be SetUserInfo`() = runTest {
        `when`(getUserUserCase(LOGIN)).thenReturn(
            Result.Success(
                data = mock()
            )
        )

        viewModel.dispatchAction(UserViewAction.Init(LOGIN))

        val viewState = viewModel.viewState.take(2).first()

        assertTrue(viewState is ViewState.SetUserInfo)
    }

    @Test
    fun `when viewAction is Init then ViewState must be Error`() = runBlocking {

        `when`(getUserUserCase(LOGIN)).thenReturn(Result.Error(Exception("No data found")))

        viewModel.dispatchAction(UserViewAction.Init(LOGIN))

        val viewState = viewModel.viewState.take(2).first()

        assertTrue(viewState is ViewState.Error)

    }

    companion object {
        private const val LOGIN = "login"
    }
}

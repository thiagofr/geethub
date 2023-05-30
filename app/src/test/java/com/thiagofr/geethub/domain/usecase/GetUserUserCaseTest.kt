package com.thiagofr.geethub.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.domain.mapper.UserMapperImpl
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.domain.repository.UserRepository
import com.thiagofr.geethub.presenter.userlist.UserListViewModel
import com.thiagofr.geethub.util.RepositoryUtil
import com.thiagofr.geethub.util.UserResponseUtil
import com.thiagofr.geethub.util.UserUtil
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUserUserCaseTest {

    @Mock
    private lateinit var repository: UserRepository

    @Mock
    private lateinit var mapper: UserMapperImpl

    @Mock
    private lateinit var getRepositoryListByUserUseCase: GetRepositoryListByUserUseCase

    private lateinit var useCase: GetUserUserCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetUserUserCaseImpl(repository, mapper, getRepositoryListByUserUseCase)
    }

    @Test
    fun `invoke should return success result with user`() = runBlocking {

        val userResponse = UserResponseUtil.getUserResponse()
        val user = UserUtil.getUser(
            id = userResponse.id,
            name = userResponse.name.orEmpty(),
            login = userResponse.login,
            avatarUrl = userResponse.avatarUrl,
            type = userResponse.type,
            following = userResponse.following ?: 1,
            followers = userResponse.followers ?: 1,
            location = userResponse.location.orEmpty(),
            repositoryList = null
        )

        `when`(repository.getUser(LOGIN)).thenReturn(Response.Success(userResponse))

        val repositoryList = RepositoryUtil.getRepositoryList()
        val successResult = Result.Success(repositoryList)

        `when`(getRepositoryListByUserUseCase(LOGIN)).thenReturn(successResult)
        `when`(mapper.map(userResponse)).thenReturn(user)

        val result = useCase.invoke(LOGIN)

        verify(repository).getUser(LOGIN)
        verify(getRepositoryListByUserUseCase).invoke(LOGIN)
        assertEquals(true, result is Result.Success)
        assertEquals(userResponse.login, (result as Result.Success<User>).data.login)
        assertEquals(repositoryList, result.data.repositoryList)
    }

    @Test
    fun `invoke should return error result when repository returns error response`() = runBlocking {
        val errorResult = Result.Error(Exception("User not found"))

        `when`(repository.getUser(LOGIN)).thenReturn(Response.Error(Exception("User not found")))

        val result = useCase.invoke(LOGIN)

        verify(repository).getUser(LOGIN)
        assertEquals(true, result is Result.Error)
        assertEquals(errorResult.exception.message, (result as Result.Error).exception.message)
    }

    @Test
    fun `invoke should return error result when getRepositoryListByUserUseCase returns error result`() =
        runBlocking {

            val userResponse = UserResponseUtil.getUserResponse()

            val errorResult = Result.Error(Exception("Failed to fetch repository list"))

            `when`(repository.getUser(LOGIN)).thenReturn(Response.Success(userResponse))
            `when`(getRepositoryListByUserUseCase(LOGIN)).thenReturn(errorResult)

            val result = useCase.invoke(LOGIN)

            verify(repository).getUser(LOGIN)
            verify(getRepositoryListByUserUseCase).invoke(LOGIN)
            assertEquals(true, result is Result.Error)
            assertEquals(errorResult.exception.message, (result as Result.Error).exception.message)
        }

    companion object {
        private const val LOGIN = "login"
    }
}

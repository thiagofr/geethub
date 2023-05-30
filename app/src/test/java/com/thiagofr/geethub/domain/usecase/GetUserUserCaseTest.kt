import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.data.remote.api.UserResponse
import com.thiagofr.geethub.domain.mapper.UserMapperImpl
import com.thiagofr.geethub.domain.model.Repository
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.model.User
import com.thiagofr.geethub.domain.repository.UserRepository
import com.thiagofr.geethub.domain.usecase.GetRepositoryListByUserUseCase
import com.thiagofr.geethub.domain.usecase.GetUserUserCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class GetUserUserCaseImplTest {
    private val repository: UserRepository = mock(UserRepository::class.java)
    private val mapper: UserMapperImpl = mock(UserMapperImpl::class.java)
    private val getRepositoryListByUserUseCase: GetRepositoryListByUserUseCase =
        mock(GetRepositoryListByUserUseCase::class.java)

    private val useCase = GetUserUserCaseImpl(repository, mapper, getRepositoryListByUserUseCase)

    @Test
    fun `invoke should return success result with user`() = runBlocking {

        val login = "exampleUser"
        val userResponse = UserResponse(
            id = 1,
            login = login,
            type = "user",
            avatarUrl = "https://example.com/avatar.jpg",
            followers = 100,
            following = 50,
            location = "Somewhere",
            name = "John Doe"
        )

        val user = User(
            id = 1,
            login = login,
            type = "user",
            avatarUrl = "https://example.com/avatar.jpg",
            followers = 100,
            following = 50,
            location = "Somewhere",
            name = "John Doe",
            repositoryList = emptyList()
        )

        val repositoryList = listOf(
            Repository(name = "repo1", fullName = "Owner/repo1", isPrivate = false),
            Repository(name = "repo2", fullName = "Owner/repo2", isPrivate = true)
        )
        val successResult = Result.Success(repositoryList)

        `when`(repository.getUser(login)).thenReturn(Response.Success(userResponse))
        `when`(getRepositoryListByUserUseCase(login)).thenReturn(successResult)
        `when`(mapper.map(userResponse)).thenReturn(user)

        val result = useCase.invoke(login)

        verify(repository).getUser(login)
        verify(getRepositoryListByUserUseCase).invoke(login)
        assertEquals(true, result is Result.Success)
        assertEquals(userResponse.login, (result as Result.Success<User>).data.login)
        assertEquals(repositoryList, (result as Result.Success<User>).data.repositoryList)
    }

    @Test
    fun `invoke should return error result when repository returns error response`() = runBlocking {
        val login = "exampleUser"
        val errorResult = Result.Error(Exception("User not found"))

        `when`(repository.getUser(login)).thenReturn(Response.Error(Exception("User not found")))

        val result = useCase.invoke(login)

        verify(repository).getUser(login)
        assertEquals(true, result is Result.Error)
        assertEquals(errorResult.exception.message, (result as Result.Error).exception.message)
    }

    @Test
    fun `invoke should return error result when getRepositoryListByUserUseCase returns error result`() = runBlocking {
        val login = "exampleUser"
        val userResponse = UserResponse(
            id = 1,
            login = login,
            type = "user",
            avatarUrl = "https://example.com/avatar.jpg",
            followers = 100,
            following = 50,
            location = "Somewhere",
            name = "John Doe"
        )
        val errorResult = Result.Error(Exception("Failed to fetch repository list"))

        `when`(repository.getUser(login)).thenReturn(Response.Success(userResponse))
        `when`(getRepositoryListByUserUseCase(login)).thenReturn(errorResult)

        val result = useCase.invoke(login)

        verify(repository).getUser(login)
        verify(getRepositoryListByUserUseCase).invoke(login)
        assertEquals(true, result is Result.Error)
        assertEquals(errorResult.exception.message, (result as Result.Error).exception.message)
    }
}

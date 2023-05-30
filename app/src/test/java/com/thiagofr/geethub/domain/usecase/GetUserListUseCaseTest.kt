import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.data.remote.api.UserResponse
import com.thiagofr.geethub.domain.mapper.UserMapperImpl
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.repository.UserRepository
import com.thiagofr.geethub.domain.usecase.GetUserListUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetUserListUseCaseImplTest {

    @Mock
    private lateinit var repository: UserRepository

    private val mapper = UserMapperImpl()
    private lateinit var useCase: GetUserListUseCaseImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = GetUserListUseCaseImpl(repository, mapper)
    }

    @Test
    fun `invoke returns success result with user list`() = runBlocking {
        val apiResponseList = listOf(
            UserResponse(
                id = 1,
                login = "user1",
                type = "User",
                avatarUrl = "https://example.com/avatar1.png",
                followers = 10,
                following = 5,
                location = "Location 1",
                name = "User 1",
            ),
            UserResponse(
                id = 2,
                login = "user2",
                type = "User",
                avatarUrl = "https://example.com/avatar2.png",
                followers = 20,
                following = 15,
                location = "Location 2",
                name = "User 2",
            )
        )
        val expectedUserList = apiResponseList.map { mapper.map(it) }
        val successResponse = Response.Success(apiResponseList)
        `when`(repository.getUserList()).thenReturn(successResponse)

        val result = useCase.invoke()

        assertEquals(Result.Success(expectedUserList), result)
    }

    @Test
    fun `invoke returns error result`() = runBlocking {
        val errorResponse = Response.Error(Exception("Failed to fetch user list"))
        `when`(repository.getUserList()).thenReturn(errorResponse)

        val result = useCase.invoke()

        assertEquals(Result.Error(errorResponse.exception), result)
    }
}

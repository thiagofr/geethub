package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.data.remote.api.RepositoryResponse
import com.thiagofr.geethub.domain.mapper.RepositoryMapperImpl
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetRepositoryListByUserUseCaseTest {

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var mapper: RepositoryMapperImpl
    private lateinit var useCase: GetRepositoryListByUserUseCaseImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mapper = RepositoryMapperImpl()
        useCase = GetRepositoryListByUserUseCaseImpl(repository, mapper)
    }

    @Test
    fun `invoke with valid login returns success result with repository list`() = runBlocking {

        val login = "example_user"
        val apiResponseList = listOf(
            RepositoryResponse(
                name = "repo1",
                fullName = "Repository 1",
                isPrivate = false
            ),
            RepositoryResponse(
                name = "repo2",
                fullName = "Repository 2",
                isPrivate = true
            )
        )
        val expectedRepositoryList = apiResponseList.map { mapper.map(it) }
        val successResponse = Response.Success(apiResponseList)
        `when`(repository.getRepositoryListByUser(login)).thenReturn(successResponse)

        val result = useCase.invoke(login)

        assertEquals(Result.Success(expectedRepositoryList), result)
    }

    @Test
    fun `invoke with invalid login returns error result`() = runBlocking {
        val login = "invalid_user"
        val errorResponse = Response.Error(Exception("User not found"))
        `when`(repository.getRepositoryListByUser(login)).thenReturn(errorResponse)

        val result = useCase.invoke(login)

        assertEquals(Result.Error(errorResponse.exception), result)
    }
}

package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.domain.mapper.RepositoryMapperImpl
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.repository.UserRepository
import com.thiagofr.geethub.util.RepositoryResponseUtil
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

        val apiResponseList = RepositoryResponseUtil.getRepositoryResponseList()
        val expectedRepositoryList = apiResponseList.map { mapper.map(it) }
        val successResponse = Response.Success(apiResponseList)
        `when`(repository.getRepositoryListByUser(LOGIN)).thenReturn(successResponse)

        val result = useCase.invoke(LOGIN)

        assertEquals(Result.Success(expectedRepositoryList), result)
    }

    @Test
    fun `invoke with invalid login returns error result`() = runBlocking {
        val errorResponse = Response.Error(Exception("User not found"))
        `when`(repository.getRepositoryListByUser(LOGIN)).thenReturn(errorResponse)

        val result = useCase.invoke(LOGIN)

        assertEquals(Result.Error(errorResponse.exception), result)
    }

    companion object {
        private const val LOGIN = "login"
    }
}

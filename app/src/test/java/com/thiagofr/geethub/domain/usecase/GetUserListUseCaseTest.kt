package com.thiagofr.geethub.domain.usecase

import com.thiagofr.geethub.data.remote.api.Response
import com.thiagofr.geethub.domain.mapper.UserMapperImpl
import com.thiagofr.geethub.domain.model.Result
import com.thiagofr.geethub.domain.repository.UserRepository
import com.thiagofr.geethub.util.UserResponseUtil
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
        MockitoAnnotations.openMocks(this)
        useCase = GetUserListUseCaseImpl(repository, mapper)
    }

    @Test
    fun `invoke returns success result with user list`() = runBlocking {

        val apiResponseList = UserResponseUtil.getUserResponseList()

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

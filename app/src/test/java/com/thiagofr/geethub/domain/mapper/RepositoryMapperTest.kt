package com.thiagofr.geethub.domain.mapper

import com.thiagofr.geethub.data.remote.api.RepositoryResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryMapperImplTest {

    private lateinit var mapper: RepositoryMapperImpl

    @Before
    fun setup() {
        mapper = RepositoryMapperImpl()
    }

    @Test
    fun map_fromApiResponse_returnsRepository() {
        val apiResponse = RepositoryResponse(
            name = "name",
            fullName = "fullname",
            isPrivate = false
        )

        val repository = mapper.map(apiResponse)

        assertEquals(apiResponse.name, repository.name)
        assertEquals(apiResponse.fullName, repository.fullName)
        assertEquals(apiResponse.isPrivate, repository.isPrivate)
    }
}

package com.thiagofr.geethub.domain.mapper

import com.thiagofr.geethub.data.remote.api.UserResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserMapperTest {

    private lateinit var mapper: UserMapperImpl

    @Before
    fun setup() {
        mapper = UserMapperImpl()
    }

    @Test
    fun map_fromUserResponse_returnsUser() {

        val userResponse = UserResponse(
            id = 123,
            login = "example_user",
            type = "User",
            avatarUrl = "https://example.com/avatar.png",
            followers = 100,
            following = 50,
            location = "Example City",
            name = "John Doe"
        )

        val user = mapper.map(userResponse)

        assertEquals(userResponse.id, user.id)
        assertEquals(userResponse.login, user.login)
        assertEquals(userResponse.type, user.type)
        assertEquals(userResponse.avatarUrl, user.avatarUrl)
        assertEquals(userResponse.followers, user.followers)
        assertEquals(userResponse.following, user.following)
        assertEquals(userResponse.location, user.location)
        assertEquals(userResponse.name, user.name)
        assertEquals(null, user.repositoryList)
    }
}

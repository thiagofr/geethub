package com.thiagofr.geethub.domain.model

data class MockResponse(
    val description: String,
    val statusCode: Int = 200,
    val filename: String
)
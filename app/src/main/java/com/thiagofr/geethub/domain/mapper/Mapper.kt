package com.thiagofr.geethub.domain.mapper

interface Mapper<E, T> {
    fun map(from: E): T
}
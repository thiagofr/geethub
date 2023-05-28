package com.thiagofr.geethub.domain

interface Mapper<E, T> {
    fun map(from: E): T
}
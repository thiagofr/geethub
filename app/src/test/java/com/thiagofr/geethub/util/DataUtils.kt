package com.thiagofr.geethub.util

import kotlin.random.Random


object DataUtils {

    fun getStringRandom(length: Int = 8): String {
        val characters =
            ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val random = Random.Default
        return (1..length)
            .map { characters[random.nextInt(characters.size)] }
            .joinToString("")
    }

    fun getBooleanRandom(): Boolean {
        val random = Random.Default
        return random.nextBoolean()
    }

    fun getIntRandom(): Int {
        val random = Random.Default
        return random.nextInt(1, 10000)
    }

}
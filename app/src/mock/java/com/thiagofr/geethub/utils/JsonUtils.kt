package com.thiagofr.geethub.utils

import android.content.Context
import android.util.Log
import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.IOException

@Throws(IOException::class)
fun readJsonFromAssets(fileName: String, context: Context): String? {
    return try {
        val assetManager = context.assets
        assetManager.open(fileName).use { inputStream ->
            val source: BufferedSource = inputStream.source().buffer()
            return source.readUtf8()
        }
    } catch (e: Exception) {
        Log.d("Erro_JSON", fileName)
        throw e
    }
}
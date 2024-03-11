package com.thiagofr.geethub.util

import retrofit2.Call
import com.thiagofr.geethub.data.remote.api.Response
import retrofit2.HttpException

fun <T : Any> Call<T>.getResult(): Response<T> {
    return try {
        val response = execute()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Response.Success(body)
            } else {
                Response.Error(Exception("Respons.e body is null"))
            }
        } else {
            Response.Error(HttpException(response))
        }
    } catch (e: Exception) {
        Response.Error(e)
    }
}
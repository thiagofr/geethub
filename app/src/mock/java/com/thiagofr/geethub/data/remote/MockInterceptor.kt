package com.thiagofr.geethub.data.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.BufferedSource
import okio.buffer
import okio.source
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException


class MockInterceptor : Interceptor, KoinComponent {

    private val context: Context by inject()

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
        val requestUrl = request.url.toUrl()

        val nameFile = getNameFile(request.method, requestUrl.path)

        val bodyTest = readJsonFromAssets(nameFile)

//        if (mockResponse != null) {

        return Response.Builder()
            .code(200)
            .message("Mocked Response")
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .body(
                bodyTest
                    ?.toResponseBody("application/json".toMediaTypeOrNull()),
            )
            .build();

//        }

//        return chain.proceed(chain.request())
    }

    private fun getNameFile(method: String, path: String): String {
        val nameFile = StringBuilder()

        nameFile.append(method)

        path.split("/").filter { it.isNotEmpty() }.forEach {
            nameFile.append("-$it")
        }

        nameFile.append(".json")

        return nameFile.toString().lowercase()

    }

    @Throws(IOException::class)
    private fun readJsonFromAssets(fileName: String): String? {
        val assetManager = context.assets
        assetManager.open(fileName).use { inputStream ->
            val source: BufferedSource = inputStream.source().buffer()
            return source.readUtf8()
        }
    }
}
package com.thiagofr.geethub.data.remote

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.thiagofr.geethub.domain.model.MockResponse
import com.thiagofr.geethub.presenter.ResponseBottomSheet
import com.thiagofr.geethub.presenter.UiFragmentManagerHolder
import com.thiagofr.geethub.utils.readJsonFromAssets
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MockInterceptor : Interceptor, KoinComponent {

    private val context: Context by inject()
    private val uiFragmentManagerHolder: UiFragmentManagerHolder by inject()

    private var shouldContinue: Boolean = false

    private var mockResponse: MockResponse? = null

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
        val requestUrl = request.url.toUrl()

        val nameFile = getNameFile(request.method, requestUrl.path)

        showBottomSheet(nameFile)

        while (!shouldContinue && mockResponse == null) {
            null
        }

        shouldContinue = false

        Log.d("Erro_JSON", "Filename: ${mockResponse?.filename} - Description: ${mockResponse?.description}")

        val fileName = mockResponse?.filename.orEmpty()

        val bodyTest = readJsonFromAssets(fileName, context)
        val statusCode = mockResponse?.statusCode ?: 200
        mockResponse = null

        return Response.Builder()
            .code(statusCode)
            .message("Mocked Response")
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .body(
                bodyTest
                    ?.toResponseBody("application/json".toMediaTypeOrNull()),
            )
            .build();
    }

    private fun getNameFile(method: String, path: String): String {
        val nameFile = StringBuilder()

        nameFile.append(method)

        path.split("/").filter { it.isNotEmpty() }.forEach {
            nameFile.append("-$it")
        }

        return nameFile.toString().lowercase()

    }

    private fun showBottomSheet(pathToResponse: String) {
        Handler(Looper.getMainLooper()).post {

            if (uiFragmentManagerHolder.fragmentManager != null) {
                val bottomSheet: BottomSheetDialogFragment = ResponseBottomSheet(pathToResponse) {
                    shouldContinue = true
                    mockResponse = it
                }
                uiFragmentManagerHolder.fragmentManager?.let {
                    bottomSheet.show(it, bottomSheet.tag)
                }
            } else {
                null
            }

        }
    }
}
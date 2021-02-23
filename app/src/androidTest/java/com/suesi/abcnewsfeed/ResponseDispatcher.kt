package com.suesi.abcnewsfeed

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.RecordedRequest
import com.squareup.okhttp.mockwebserver.Dispatcher
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

enum class ResponseType {
    SUCCESS,
    ERROR
}

class ResponseDispatcher(
    private val responseType: ResponseType,
    private val context: Context = InstrumentationRegistry.getInstrumentation().context
) : Dispatcher() {
    override fun dispatch(request: RecordedRequest?): MockResponse {
        when (responseType) {
            ResponseType.SUCCESS -> {
                val responseFile = "news_feed.json"

                val responseBody = asset(context, responseFile)
                return MockResponse().setResponseCode(200).setBody(responseBody)
            }

            ResponseType.ERROR -> {
                return MockResponse().setResponseCode(404)
            }
        }
    }

    private fun asset(context: Context, assetPath: String): String {
        try {
            val inputStream = context.assets.open("resources/$assetPath")
            return inputStreamToString(inputStream, "UTF-8")
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    private fun inputStreamToString(inputStream: InputStream, charsetName: String): String {
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, charsetName)
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}
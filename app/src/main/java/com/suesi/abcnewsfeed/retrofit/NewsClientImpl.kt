package com.suesi.abcnewsfeed.retrofit

import android.content.Context
import com.google.gson.GsonBuilder
import com.suesi.abcnewsfeed.retrofit.response.RssFeedsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.rss2json.com"
private const val REQUEST_TIMEOUT_DURATION = 30L

class NewsClientImpl(private val context : Context) : NewsClient {

    private val apiService : NewsService

    init {
        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create()

        apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createRequestInterceptorClient())
            .build()
            .create(NewsService::class.java)
    }

    private fun createRequestInterceptorClient(): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)

        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .cache(cache)
            .build()
    }

    override suspend fun loadNews(): Response<RssFeedsResponse> = withContext(Dispatchers.IO) {
        return@withContext apiService.loadNews("http://www.abc.net.au/news/feed/51120/rss.xml").execute()
    }
}
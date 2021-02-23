package com.suesi.abcnewsfeed.retrofit

import com.suesi.abcnewsfeed.retrofit.response.RssFeedsResponse
import retrofit2.Response

interface NewsClient {
    suspend fun loadNews(rssUrl : String) : Response<RssFeedsResponse>
}
package com.suesi.abcnewsfeed.retrofit

import com.suesi.abcnewsfeed.retrofit.response.RssFeedsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v1/api.json")
    fun loadNews(@Query(value = "rss_url") rssUrl : String) : Call<RssFeedsResponse>
}
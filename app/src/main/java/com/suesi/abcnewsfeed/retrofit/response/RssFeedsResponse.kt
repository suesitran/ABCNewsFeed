package com.suesi.abcnewsfeed.retrofit.response

data class RssFeedsResponse(
    val status: String,
    val feed: FeedResponse,
    val items: List<FeedItemResponse>
)

data class FeedResponse(
    val url: String,
    val title: String,
    val link: String,
    val author: String,
    val description: String,
    val image: String
)

data class FeedItemResponse(
    val title: String,
    val pubDate: String,
    val link: String,
    val guid: String,
    val author: String,
    val thumbnail: String,
    val description: String,
    val content: String,
    val enclosure: EnclosureResponse,
    val categories: List<String>
)

data class EnclosureResponse(val link: String, val type: String, val thumbnail: String)

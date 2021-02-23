package com.suesi.abcnewsfeed.usecase

import com.suesi.abcnewsfeed.data.FeedDisplayable

interface RetrieveNewsFeedFromServerUseCase {
    suspend fun retrieveNewsFeed() : Result

    sealed class Result {
        data class Success(val items : List<FeedDisplayable>) : Result()
        object Error : Result()
    }
}
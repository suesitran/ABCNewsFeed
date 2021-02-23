package com.suesi.abcnewsfeed.features.content.model.usecase

import com.suesi.abcnewsfeed.features.content.model.FeedDisplayable

interface RetrieveNewsFeedFromServerUseCase {
    suspend fun retrieveNewsFeed() : Result

    sealed class Result {
        data class Success(val items : List<FeedDisplayable>) : Result()
        object Error : Result()
    }
}
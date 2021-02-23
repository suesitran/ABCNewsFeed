package com.suesi.abcnewsfeed.usecase

import com.suesi.abcnewsfeed.data.FeedDisplayable
import com.suesi.abcnewsfeed.retrofit.NewsClient
import java.text.SimpleDateFormat

class RetrieveNewsFeedFromServerUseCaseImpl(val newsClient: NewsClient) : RetrieveNewsFeedFromServerUseCase {

    override suspend fun retrieveNewsFeed(): RetrieveNewsFeedFromServerUseCase.Result {
        val response = newsClient.loadNews()

        if (response.isSuccessful) {
            return response.body()?.let { feedResposne ->
                // body is not null, convert all items into FeedDisplayable
                val list = feedResposne.items.map { item ->
                    FeedDisplayable(
                        item.title,
                        item.thumbnail,
                        item.enclosure.link,
                        // convert to UI displayable date
                        convertToUiDate(item.pubDate),
                        item.content
                    )
                }
                RetrieveNewsFeedFromServerUseCase.Result.Success(list)
            } ?: RetrieveNewsFeedFromServerUseCase.Result.Error
        } else {
            return RetrieveNewsFeedFromServerUseCase.Result.Error
        }
    }

    private fun convertToUiDate(serverDate : String) : String {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .parse(serverDate)

        return SimpleDateFormat("MMM dd, yyyy KK:mm a")
            .format(date)
    }
}
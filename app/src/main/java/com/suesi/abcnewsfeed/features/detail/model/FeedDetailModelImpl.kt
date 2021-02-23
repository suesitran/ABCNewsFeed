package com.suesi.abcnewsfeed.features.detail.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suesi.abcnewsfeed.data.FeedDisplayable
import com.suesi.abcnewsfeed.usecase.RetrieveNewsFeedFromServerUseCase
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class FeedDetailModelImpl(
    private val retrieveNewsFeedFromServerUseCase : RetrieveNewsFeedFromServerUseCase,
    private val position: Int) : FeedDetailModel() {

    private val _feeds = MutableLiveData<List<FeedDisplayable>>()
    override val feeds: LiveData<List<FeedDisplayable>> = _feeds

    init {
        reloadFeeds()
    }

    override fun reloadFeeds() {
        launch {

            when (val retrieveNewsFeedResult = retrieveNewsFeedFromServerUseCase.retrieveNewsFeed()) {
                is RetrieveNewsFeedFromServerUseCase.Result.Success -> {
                    val selectedItem = retrieveNewsFeedResult.items[position]

                    val list = ArrayList(retrieveNewsFeedResult.items)
                    list.removeAt(position)
                    list.add(0, selectedItem)

                    _feeds.postValue(list)
                }
                is RetrieveNewsFeedFromServerUseCase.Result.Error -> {
                    // must not happen since it's loading from cache
                }
            }
        }
    }
}
package com.suesi.abcnewsfeed.features.content.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suesi.abcnewsfeed.features.content.model.usecase.RetrieveNewsFeedFromServerUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedListViewModelImpl(val retrieveNewsFeedFromServerUseCase: RetrieveNewsFeedFromServerUseCase) : FeedListViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    override val isError: LiveData<Boolean> = _isError

    private val _feeds = MutableLiveData<List<FeedDisplayable>>()
    override val feeds: LiveData<List<FeedDisplayable>> = _feeds

    init {
        loadNews()
    }

    override fun loadNews() {
        launch {
            _isLoading.postValue(true)

            when (val retrieveNewsFeedResult = retrieveNewsFeedFromServerUseCase.retrieveNewsFeed()) {
                is RetrieveNewsFeedFromServerUseCase.Result.Success -> {
                    android.util.Log.d("--SUESI--","result success" )
                    _feeds.postValue(retrieveNewsFeedResult.items)
                }
                is RetrieveNewsFeedFromServerUseCase.Result.Error -> {
                    android.util.Log.d("--SUESI--","result error" )
                    _isError.postValue(true)
                }
            }

            android.util.Log.d("--SUESI--", "load news completed")

            _isLoading.postValue(false)
        }
    }
}
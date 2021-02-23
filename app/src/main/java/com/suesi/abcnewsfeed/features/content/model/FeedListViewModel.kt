package com.suesi.abcnewsfeed.features.content.model

import androidx.lifecycle.LiveData
import com.suesi.abcnewsfeed.basemodel.BaseModel
import com.suesi.abcnewsfeed.data.FeedDisplayable

abstract class FeedListViewModel : BaseModel() {
    abstract val isLoading : LiveData<Boolean>

    abstract val isError : LiveData<Boolean>

    abstract val feeds : LiveData<List<FeedDisplayable>>

    abstract fun loadNews()
}
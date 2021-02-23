package com.suesi.abcnewsfeed.features.detail.model

import androidx.lifecycle.LiveData
import com.suesi.abcnewsfeed.basemodel.BaseModel
import com.suesi.abcnewsfeed.data.FeedDisplayable

abstract class FeedDetailModel : BaseModel() {
    abstract val feeds : LiveData<List<FeedDisplayable>>

    abstract fun reloadFeeds()
}
package com.suesi.abcnewsfeed.features.content.model

import androidx.lifecycle.LiveData
import com.suesi.abcnewsfeed.basemodel.BaseModel
import com.suesi.abcnewsfeed.features.content.model.usecase.RetrieveNewsFeedFromServerUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

abstract class FeedListViewModel : BaseModel() {
    abstract val isLoading : LiveData<Boolean>

    abstract val isError : LiveData<Boolean>

    abstract val feeds : LiveData<List<FeedDisplayable>>

    abstract fun loadNews()
}
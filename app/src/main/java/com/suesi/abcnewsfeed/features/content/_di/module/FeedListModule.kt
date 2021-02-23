package com.suesi.abcnewsfeed.features.content._di.module

import com.suesi.abcnewsfeed.features.content.model.FeedListViewModel
import com.suesi.abcnewsfeed.features.content.model.FeedListViewModelImpl
import com.suesi.abcnewsfeed.usecase.RetrieveNewsFeedFromServerUseCase
import com.suesi.abcnewsfeed.usecase.retrievefeedsusecase._di.module.RetrieveNewsFeedFromServerUseCaseModule
import dagger.Module
import dagger.Provides

@Module(includes = [RetrieveNewsFeedFromServerUseCaseModule::class])
class FeedListModule {
    @Provides
    fun provideViewModel(useCase : RetrieveNewsFeedFromServerUseCase): FeedListViewModel = FeedListViewModelImpl(useCase)
}
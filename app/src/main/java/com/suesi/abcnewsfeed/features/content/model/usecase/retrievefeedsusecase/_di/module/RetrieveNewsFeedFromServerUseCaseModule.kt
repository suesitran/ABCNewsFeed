package com.suesi.abcnewsfeed.features.content.model.usecase.retrievefeedsusecase._di.module

import com.suesi.abcnewsfeed.ActiveRuntime
import com.suesi.abcnewsfeed.features.content.model.usecase.RetrieveNewsFeedFromServerUseCase
import com.suesi.abcnewsfeed.features.content.model.usecase.RetrieveNewsFeedFromServerUseCaseImpl
import com.suesi.abcnewsfeed.retrofit.NewsClient
import dagger.Module
import dagger.Provides

@Module
class RetrieveNewsFeedFromServerUseCaseModule {
    @ActiveRuntime
    @Provides
    fun provideRetrieveNewsFeedFromServerUseCase(newsClient: NewsClient) : RetrieveNewsFeedFromServerUseCase = RetrieveNewsFeedFromServerUseCaseImpl(newsClient)
}
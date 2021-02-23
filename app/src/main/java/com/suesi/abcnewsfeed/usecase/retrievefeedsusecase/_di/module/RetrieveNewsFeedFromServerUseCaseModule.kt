package com.suesi.abcnewsfeed.usecase.retrievefeedsusecase._di.module

import com.suesi.abcnewsfeed.ActiveRuntime
import com.suesi.abcnewsfeed.usecase.RetrieveNewsFeedFromServerUseCase
import com.suesi.abcnewsfeed.usecase.RetrieveNewsFeedFromServerUseCaseImpl
import com.suesi.abcnewsfeed.retrofit.NewsClient
import dagger.Module
import dagger.Provides

@Module
class RetrieveNewsFeedFromServerUseCaseModule {
    @ActiveRuntime
    @Provides
    fun provideRetrieveNewsFeedFromServerUseCase(impl: RetrieveNewsFeedFromServerUseCaseImpl) : RetrieveNewsFeedFromServerUseCase = impl
}
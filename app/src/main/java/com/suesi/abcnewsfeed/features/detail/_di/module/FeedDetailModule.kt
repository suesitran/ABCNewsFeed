package com.suesi.abcnewsfeed.features.detail._di.module

import com.suesi.abcnewsfeed.features.detail.model.FeedDetailModel
import com.suesi.abcnewsfeed.features.detail.model.FeedDetailModelImpl
import com.suesi.abcnewsfeed.usecase.RetrieveNewsFeedFromServerUseCase
import com.suesi.abcnewsfeed.usecase.retrievefeedsusecase._di.module.RetrieveNewsFeedFromServerUseCaseModule
import dagger.Module
import dagger.Provides

@Module(includes = [RetrieveNewsFeedFromServerUseCaseModule::class])
class FeedDetailModule(private val position : Int) {
    @Provides
    fun provideViewModel(useCase : RetrieveNewsFeedFromServerUseCase): FeedDetailModel = FeedDetailModelImpl(useCase, position)

}
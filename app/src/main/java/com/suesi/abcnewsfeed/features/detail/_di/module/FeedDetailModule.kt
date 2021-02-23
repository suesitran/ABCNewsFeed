package com.suesi.abcnewsfeed.features.detail._di.module

import com.suesi.abcnewsfeed.features.detail.model.FeedDetailModel
import com.suesi.abcnewsfeed.features.detail.model.FeedDetailModelImpl
import com.suesi.abcnewsfeed.usecase.RetrieveNewsFeedFromServerUseCase
import com.suesi.abcnewsfeed.usecase.retrievefeedsusecase._di.module.RetrieveNewsFeedFromServerUseCaseModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [RetrieveNewsFeedFromServerUseCaseModule::class])
class FeedDetailModule(private val position : Int) {
    @Provides
    fun provideViewModel(impl : FeedDetailModelImpl): FeedDetailModel = impl

    @Named("SelectedPosition")
    @Provides
    fun provideSelectedPosition() : Int = position

}
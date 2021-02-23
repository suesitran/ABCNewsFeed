package com.suesi.abcnewsfeed._di.module

import com.suesi.abcnewsfeed.retrofit.NewsClient
import com.suesi.abcnewsfeed.retrofit.NewsClientImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideNewsClient() : NewsClient = NewsClientImpl()
}
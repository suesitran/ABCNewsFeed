package com.suesi.abcnewsfeed._di.module

import com.suesi.abcnewsfeed.retrofit.NewsClient
import com.suesi.abcnewsfeed.retrofit.NewsClientImpl
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun provideNewsClient() : NewsClient = NewsClientImpl()
}
package com.suesi.abcnewsfeed._di.module

import android.content.Context
import com.suesi.abcnewsfeed.retrofit.NewsClient
import com.suesi.abcnewsfeed.retrofit.NewsClientImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context : Context, private val baseUrl : String) {
    @Provides
    @Singleton
    fun provideNewsClient() : NewsClient = NewsClientImpl(context, baseUrl)
}
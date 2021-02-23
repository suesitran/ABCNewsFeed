package com.suesi.abcnewsfeed._di.component

import com.suesi.abcnewsfeed.MainActivity
import com.suesi.abcnewsfeed._di.module.ApplicationModule
import com.suesi.abcnewsfeed.retrofit.NewsClient
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(activity : MainActivity)

    fun provideNewsClient() : NewsClient
}
package com.suesi.abcnewsfeed

import android.app.Application
import com.suesi.abcnewsfeed._di.component.ApplicationComponent
import com.suesi.abcnewsfeed._di.component.DaggerApplicationComponent
import com.suesi.abcnewsfeed._di.module.ApplicationModule

const val BASE_URL = "https://api.rss2json.com"
open class NewsApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this, getBaseUrl()))
            .build()
    }

    open fun getBaseUrl(): String = BASE_URL
}
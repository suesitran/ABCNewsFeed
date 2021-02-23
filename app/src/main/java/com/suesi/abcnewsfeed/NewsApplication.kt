package com.suesi.abcnewsfeed

import android.app.Application
import com.suesi.abcnewsfeed._di.component.ApplicationComponent
import com.suesi.abcnewsfeed._di.component.DaggerApplicationComponent
import com.suesi.abcnewsfeed._di.module.ApplicationModule

class NewsApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}
package com.suesi.abcnewsfeed

import android.app.Application
import com.suesi.abcnewsfeed._di.component.DaggerApplicationComponent
import com.suesi.abcnewsfeed._di.module.ApplicationModule

class NewsApplication : Application() {
    val component =
        DaggerApplicationComponent.builder().applicationModule(ApplicationModule()).build()
}
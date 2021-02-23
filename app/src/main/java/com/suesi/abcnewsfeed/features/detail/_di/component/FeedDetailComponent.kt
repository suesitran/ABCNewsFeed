package com.suesi.abcnewsfeed.features.detail._di.component

import com.suesi.abcnewsfeed.ActiveRuntime
import com.suesi.abcnewsfeed._di.component.ApplicationComponent
import com.suesi.abcnewsfeed.features.detail._di.module.FeedDetailModule
import com.suesi.abcnewsfeed.features.detail.view.FeedDetailFragment
import dagger.Component

@ActiveRuntime
@Component(dependencies = [ApplicationComponent::class], modules = [FeedDetailModule::class])
interface FeedDetailComponent {
    fun inject(fragment : FeedDetailFragment)
}
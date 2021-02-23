package com.suesi.abcnewsfeed.features.content._di.component

import com.suesi.abcnewsfeed.ActiveRuntime
import com.suesi.abcnewsfeed._di.component.ApplicationComponent
import com.suesi.abcnewsfeed.features.content._di.module.FeedListModule
import com.suesi.abcnewsfeed.features.content.view.FeedListFragment
import dagger.Component

@ActiveRuntime
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FeedListModule::class]
)
interface FeedListComponent {
    fun inject(feedListFragment: FeedListFragment)
}
package com.glovoapp.feed.di

import com.glovoapp.feed.FeedViewModel
import com.glovoapp.feed.data.FeedRepository
import dagger.Component

@Component(modules = [FeedModule::class])
interface APIComponent {
    fun inject(repo: FeedRepository)
    fun inject(viewModel: FeedViewModel)
}
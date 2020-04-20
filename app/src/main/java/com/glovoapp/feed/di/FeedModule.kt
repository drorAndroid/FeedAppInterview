package com.glovoapp.feed.di

import com.glovoapp.feed.data.FeedRepository
import com.glovoapp.feed.data.FeedService
import dagger.Module
import dagger.Provides

@Module
class FeedModule {

    @Provides
    fun provideFeedService(): FeedService {
        return FeedService()
    }

    @Provides
    fun provideFeedRepository(): FeedRepository {
        return FeedRepository()
    }
}
package com.glovoapp.feed.data


class FeedRepository(private val feedService: FeedService) {

    fun getLatestItems(onComplete: (List<FeedItem>) -> Unit) {
        feedService.getLatestItems { items ->
            onComplete(items)
        }
    }

}
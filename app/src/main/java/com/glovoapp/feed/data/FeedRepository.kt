package com.glovoapp.feed.data

import android.util.Log
import com.glovoapp.feed.di.DaggerAPIComponent
import io.reactivex.Single
import java.util.*
import javax.inject.Inject


class FeedRepository {
    @Inject
    lateinit var feedService: FeedService

    init {
        DaggerAPIComponent.create().inject(this)
    }

    fun getLatestItems() : Single<List<FeedItem>> {
        return Single.create { emitter ->
            feedService.getLatestItems { items ->
                Log.d("success", items.toString())
                emitter.onSuccess(items)
            }
        }
    }

    fun getOlderItems(date: Date) : Single<List<FeedItem>> {
        return Single.create { emitter ->
            feedService.getOlderItems(date) { items ->
                Log.d("success", items.toString())
                emitter.onSuccess(items)
            }
        }
    }
}
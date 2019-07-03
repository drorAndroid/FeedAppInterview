package com.glovoapp.feed

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.glovoapp.feed.data.FeedItem
import com.glovoapp.feed.data.FeedRepository
import com.glovoapp.feed.data.FeedService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val feedItemAdapter = FeedItemAdapter()

        recycler.apply {
            layoutManager = LinearLayoutManager(this@FeedActivity)
            adapter = feedItemAdapter
        }

        //Feel free to use kotlin Coroutines or RxJava
        requestWithCoroutines(feedItemAdapter)
        //requestWithRx(feedItemAdapter)
    }

    private fun requestWithCoroutines(feedItemAdapter: FeedItemAdapter) {
        GlobalScope.launch {
            FeedRepository(FeedService()).getLatestItems { items ->
                GlobalScope.launch(Dispatchers.Main) {

                    feedItemAdapter.items = items
                    feedItemAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun requestWithRx(feedItemAdapter: FeedItemAdapter) {
        Single.create<List<FeedItem>> { emitter ->

            FeedService().getLatestItems { items ->
                emitter.onSuccess(items)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { items ->
                feedItemAdapter.items = items
                feedItemAdapter.notifyDataSetChanged()
            }
            .subscribe()
    }
}



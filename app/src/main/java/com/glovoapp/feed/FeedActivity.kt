package com.glovoapp.feed

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.glovoapp.feed.data.FeedRepository
import com.glovoapp.feed.data.FeedService
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

        GlobalScope.launch {
            FeedRepository(FeedService()).getLatestItems { items ->
                GlobalScope.launch(Dispatchers.Main) {

                    feedItemAdapter.items = items
                    feedItemAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}


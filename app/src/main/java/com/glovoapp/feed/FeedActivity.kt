package com.glovoapp.feed

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.glovoapp.feed.data.FeedRepository
import com.glovoapp.feed.data.FeedService
import kotlinx.android.synthetic.main.activity_feed.*


class FeedActivity : AppCompatActivity() {

    private val feedItemAdapter = FeedItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        recycler.layoutManager = LinearLayoutManager(this@FeedActivity)
        recycler.adapter = feedItemAdapter

        val feedRepository = FeedRepository(FeedService())

        feedRepository.getLatestItems { items ->
            feedItemAdapter.items = items
            feedItemAdapter.notifyDataSetChanged()
        }
    }
}

package com.glovoapp.feed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedItemAdapter: FeedItemAdapter
    private val itemsThreshold = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        feedItemAdapter = FeedItemAdapter()

        recycler.apply {
            layoutManager = LinearLayoutManager(this@FeedActivity)
            adapter = feedItemAdapter
        }

        recycler.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val currLastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if(currLastVisibleItem == feedItemAdapter.items.size - 5) {
                    val item = feedItemAdapter.items[currLastVisibleItem]
                    viewModel.getOlderItems(item.createdAt)
                }
            }
        })

        //Feel free to use kotlin Coroutines or RxJava
//        requestWithCoroutines(feedItemAdapter)
        viewModel.getLatestItems()



        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.items.observe(this, Observer { items->
            items?.let {
                feedItemAdapter.items = items
                feedItemAdapter.notifyDataSetChanged()
            }

        })
    }
}



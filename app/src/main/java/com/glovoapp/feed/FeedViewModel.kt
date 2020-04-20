package com.glovoapp.feed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glovoapp.feed.data.FeedItem
import com.glovoapp.feed.data.FeedRepository
import com.glovoapp.feed.di.DaggerAPIComponent
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class FeedViewModel: ViewModel() {
    @Inject
    lateinit var repo: FeedRepository
    var items = MutableLiveData<MutableList<FeedItem>?>(null)
    private val disposable = CompositeDisposable()

    init {
        DaggerAPIComponent.create().inject(this)
    }

    fun getLatestItems() {
        disposable.add(
            repo.getLatestItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<FeedItem>>() {
                    override fun onSuccess(t: List<FeedItem>) {
                        var currItems = items.value
                        if(currItems == null) {
                            currItems = mutableListOf()
                        }
                        currItems.addAll(t)
                        items.value = currItems
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", e.toString())
                    }

                })
        )
    }

    fun getOlderItems(date: Date) {
        disposable.add(
            repo.getOlderItems(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<FeedItem>>() {
                    override fun onSuccess(t: List<FeedItem>) {
                        var currItems = items.value
                        if(currItems == null) {
                            currItems = mutableListOf()
                        }

                        currItems.addAll(t)
                        items.value = currItems
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", e.toString())
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}
package com.glovoapp.feed.data

import android.os.Handler
import java.text.SimpleDateFormat
import java.util.*

class FeedService(private val dateProvider: DateProvider = SystemDateProvider) {

    val dateFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())

    fun getNewerItems(after: Date, limit: Int = 10, callback: (List<FeedItem>) -> Unit) {
        getItems(after, 1..limit, callback)
    }

    fun getOlderItems(before: Date, limit: Int = 10, callback: (List<FeedItem>) -> Unit) {
        getItems(before, -limit..-1, callback)
    }

    fun getLatestItems(limit: Int = 10, callback: (List<FeedItem>) -> Unit) {
        getOlderItems(dateProvider.now(), limit, callback)
    }

    private fun getItems(date: Date, range: IntRange, callback: (List<FeedItem>) -> Unit) {
        val items: MutableList<FeedItem> = mutableListOf()

        val todayCalendar = Calendar.getInstance().apply {
            time = dateProvider.now()
        }
        var futureCalendar: Calendar

        for (i in range.reversed()) {
            futureCalendar = Calendar.getInstance().apply {
                this.time = date
                this.add(Calendar.SECOND, i)
            }

            if (futureCalendar > todayCalendar) {
                continue
            }

            val id =
                futureCalendar.get(Calendar.HOUR) + futureCalendar.get(Calendar.MINUTE) + futureCalendar.get(Calendar.SECOND)
            val createdAt = futureCalendar.time
            val title = dateFormat.format(createdAt)

            items.add(FeedItem(id, createdAt, title))
        }

        Handler().postDelayed({
            callback(items)
        }, 2500)
    }
}
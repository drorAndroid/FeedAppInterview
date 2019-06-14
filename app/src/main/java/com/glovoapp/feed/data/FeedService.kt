package com.glovoapp.feed.data

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

        val items = getItems(date, range)

        GlobalScope.launch {
            delay(2500)
            callback(items)
        }
    }

    private fun getItems(date: Date, range: IntRange): List<FeedItem> {

        val todayCalendar = Calendar.getInstance().apply {
            time = dateProvider.now()
        }
        var futureCalendar: Calendar

        return range.reversed().mapNotNull { i ->
            futureCalendar = Calendar.getInstance().apply {
                this.time = date
                this.add(Calendar.SECOND, i)
            }

            if (futureCalendar > todayCalendar) {
                null
            } else {
                val id =
                    futureCalendar.get(Calendar.HOUR)
                +futureCalendar.get(Calendar.MINUTE)
                +futureCalendar.get(Calendar.SECOND)

                val createdAt = futureCalendar.time
                val title = dateFormat.format(createdAt)

                FeedItem(id, createdAt, title)
            }

        }
    }
}
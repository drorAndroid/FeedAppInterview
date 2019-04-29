package com.glovoapp.feed

import com.glovoapp.feed.data.FeedService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class FeedServiceTest {

    private val service = FeedService()

    @Test
    fun testBefore() {
        val date = service.dateFormat.parse("01:00:30")

        service.getOlderItems(date, 5) { items ->
            assert(items.count() == 5)
            assert(items[0].title == "01:00:29")
            assert(items[1].title == "01:00:28")
            assert(items[2].title == "01:00:27")
            assert(items[3].title == "01:00:26")
            assert(items[4].title == "01:00:25")
        }
    }

    @Test
    fun testAfter() {
        val date = service.dateFormat.parse("01:00:30")

        service.getNewerItems(date, 5) { items ->
            assert(items.count() == 5)
            assert(items[0].title == "01:00:35")
            assert(items[1].title == "01:00:34")
            assert(items[2].title == "01:00:33")
            assert(items[3].title == "01:00:32")
            assert(items[4].title == "01:00:31")
        }
    }

    @Test
    fun testNow() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, -2)

        val date = calendar.time

        service.getNewerItems(date, 5) { items ->
            assert(items.count() == 2)
        }
    }
}
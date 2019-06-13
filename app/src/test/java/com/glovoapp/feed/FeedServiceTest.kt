package com.glovoapp.feed

import com.glovoapp.feed.data.DateProvider
import com.glovoapp.feed.data.FeedService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class FeedServiceTest {

    private val dateProvider = mock(DateProvider::class.java)
    private val service = FeedService(dateProvider)

    @Test
    fun testBefore() {
        val date = service.dateFormat.parse("01:00:30")
        `when`(dateProvider.now()).thenReturn(date)
        val limit = 5

        service.getOlderItems(date, limit) { items ->
            assert(items.count() == limit)
            assert(items[0].title == "01:00:29")
            assert(items[1].title == "01:00:28")
            assert(items[2].title == "01:00:27")
            assert(items[3].title == "01:00:26")
            assert(items[4].title == "01:00:25")
        }
    }

    @Test
    fun testAfter() {
        val limit = 5
        val date = service.dateFormat.parse("01:00:30")
        val offsetToEnsureThereAreItems = (limit + 2) * 1000
        `when`(dateProvider.now()).thenReturn(Date(date.time + offsetToEnsureThereAreItems))

        service.getNewerItems(date, limit) { items ->
            assert(items.count() == limit)
            assert(items[0].title == "01:00:35")
            assert(items[1].title == "01:00:34")
            assert(items[2].title == "01:00:33")
            assert(items[3].title == "01:00:32")
            assert(items[4].title == "01:00:31")
        }
    }

    @Test
    fun testLatest() {
        val date = Date()
        `when`(dateProvider.now()).thenReturn(date)
        val limit = 5

        service.getLatestItems(limit) { items ->
            assert(items.count() == limit)
            assert(items[0].title == service.dateFormat.format(Date(date.time - 1000)))
            assert(items[1].title == service.dateFormat.format(Date(date.time - 2000)))
            assert(items[2].title == service.dateFormat.format(Date(date.time - 3000)))
            assert(items[3].title == service.dateFormat.format(Date(date.time - 4000)))
            assert(items[4].title == service.dateFormat.format(Date(date.time - 5000)))
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
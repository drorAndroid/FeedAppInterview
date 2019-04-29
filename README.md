#  Endless Feed Challenge

## Description

This is a simple app that shows an endless feed of items. Each item is represented as the time of its creation. The feed generates a new item every second and indefinitely spans into the past. The items are arranged in descending order from newest at the top to oldest at the bottom.

### Key Classes

  - `FeedActivity` RecyclerView that shows the feed.
  - `FeedService` Provides an API to get newer/older items after/before a given date. There is an artificial delay intended to emulate network latency.

## Goal

1. Implement `FeedRepository` that abstracts API management, takes care of pagination and enables seamless infinite scrolling experience.
2. Refactor the code into a Presentation pattern you want: MVP, MVVM, etc.

## Tasks

1. **Implement seamless one directional scrolling** (from newest to oldest items). After launching the app I should be able to seamlessly scroll down to older items.
2. **Implement seamless bi-directional scrolling** (without displaying items that were generated after the app was launched). After launching the app I should be able to seamlessly scroll down to older items and up to newer items up to the first item that was shown when the app was launched.
3. **Implement Pull-to-Refresh to display newly generated items**. After launching the app I should be able to seamlessly scroll down to older items and up to newer items up to the first item that was shown when the app was launched. When I'm at the top of the feed I should be able to perform Pull-to-Refresh to fetch and display any new items that were generated.
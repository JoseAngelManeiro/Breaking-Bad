package com.mediquo.breakingbad.data.cache

import com.mediquo.breakingbad.domain.model.Quote

class QuoteCache(private val map: MutableMap<String, MutableList<Quote>> = mutableMapOf()) {

  fun save(quotes: List<Quote>) {
    map.clear()
    quotes.forEach { quote ->
      val value = map[quote.author] ?: mutableListOf()
      value.add(quote)
      map[quote.author] = value
    }
  }

  fun get(key: String): List<Quote>? = map[key]
}

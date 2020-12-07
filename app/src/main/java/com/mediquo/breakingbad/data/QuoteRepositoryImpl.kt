package com.mediquo.breakingbad.data

import com.mediquo.breakingbad.data.api.ApiClient
import com.mediquo.breakingbad.data.cache.QuoteCache
import com.mediquo.breakingbad.data.entity.QuoteEntity
import com.mediquo.breakingbad.data.mapper.QuoteMapper
import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.model.Quote
import com.mediquo.breakingbad.domain.repository.QuoteRepository

internal class QuoteRepositoryImpl(
    private val apiClient: ApiClient,
    private val cache: QuoteCache,
    private val mapper: QuoteMapper
): QuoteRepository {

    override fun getQuotes(name: String): Either<Exception, List<Quote>> {
        return object : PrefetchLocalData<List<QuoteEntity>, List<Quote>>() {
            override fun loadFromLocal() = cache.get(name)

            override fun shouldFetch(data: List<Quote>?) = !cache.isValid()

            override fun loadFromService() = apiClient.getQuotes()

            override fun saveServiceResult(item: List<QuoteEntity>) {
                cache.save(item.map { mapper.toDomain(it) })
            }
        }.load()
    }
}
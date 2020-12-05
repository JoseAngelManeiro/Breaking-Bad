package com.mediquo.breakingbad.domain.repository

import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.model.Quote

interface QuoteRepository {
    fun getQuotes(name: String): Either<Exception, List<Quote>>
}
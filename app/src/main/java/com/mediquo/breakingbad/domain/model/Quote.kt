package com.mediquo.breakingbad.domain.model

data class Quote (
    val quoteId: Int,
    val quote: String,
    val author: String,
    val series: String
)
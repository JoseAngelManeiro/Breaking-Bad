package com.mediquo.breakingbad.data.entity

import com.google.gson.annotations.SerializedName

data class QuoteEntity (
    @SerializedName("quote_id")
    val quoteId: Int,
    val quote: String,
    val author: String,
    val series: String
)
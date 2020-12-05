package com.mediquo.breakingbad.data.mapper

import com.mediquo.breakingbad.data.entity.QuoteEntity
import com.mediquo.breakingbad.domain.model.Quote

class QuoteMapper: Mapper<QuoteEntity, Quote> {

  override fun toDomain(dataModel: QuoteEntity): Quote {
    return Quote(
      quote = dataModel.quote,
      quoteId = dataModel.quoteId,
      author = dataModel.author,
      series = dataModel.series
    )
  }
}
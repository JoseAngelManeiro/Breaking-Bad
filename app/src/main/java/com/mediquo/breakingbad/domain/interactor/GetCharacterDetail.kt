package com.mediquo.breakingbad.domain.interactor

import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.domain.model.Quote
import com.mediquo.breakingbad.domain.repository.CharacterRepository
import com.mediquo.breakingbad.domain.repository.QuoteRepository

class GetCharacterDetail(
  private val characterRepository: CharacterRepository,
  private val quoteRepository: QuoteRepository
) : Interactor<String, GetCharacterDetail.Response> {

  data class Response(
    val character: Character,
    val quotes: List<Quote>
  )

  override fun invoke(request: String): Either<Exception, Response> {
    val character = characterRepository.getCharacter(request)
    val quotes = quoteRepository.getQuotes(request)
    return if (character.isRight && quotes.isRight) {
      Either.right(Response(character.rightValue, quotes.rightValue))
    } else {
      val exception = if (character.isLeft) character.leftValue else quotes.leftValue
      Either.left(exception)
    }
  }
}

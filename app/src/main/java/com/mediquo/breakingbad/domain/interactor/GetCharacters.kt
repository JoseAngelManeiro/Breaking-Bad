package com.mediquo.breakingbad.domain.interactor

import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.domain.repository.CharacterRepository

class GetCharacters(
  private val characterRepository: CharacterRepository
) : Interactor<Unit, List<Character>> {

  override fun invoke(request: Unit): Either<Exception, List<Character>> {
    return characterRepository.getCharacters()
  }
}

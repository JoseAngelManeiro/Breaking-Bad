package com.mediquo.breakingbad.domain.repository

import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.model.Character

interface CharacterRepository {
    fun getCharacters(): Either<Exception, List<Character>>
    fun getCharacter(name: String): Either<Exception, Character>
}
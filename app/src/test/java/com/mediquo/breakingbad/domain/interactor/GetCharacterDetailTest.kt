package com.mediquo.breakingbad.domain.interactor

import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.domain.model.Quote
import com.mediquo.breakingbad.domain.repository.CharacterRepository
import com.mediquo.breakingbad.domain.repository.QuoteRepository
import com.mediquo.breakingbad.util.mock
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import org.mockito.BDDMockito.*
import java.lang.Exception

class GetCharacterDetailTest {

    private val characterRepository = mock<CharacterRepository>()
    private val quoteRepository = mock<QuoteRepository>()

    private lateinit var useCase: GetCharacterDetail

    @Before
    fun setUp() {
        useCase = GetCharacterDetail(characterRepository, quoteRepository)
    }

    @Test
    fun `invokes the repositories and composes the detail correctly`() {
        val name = "Walter White"
        val character = mock<Character>()
        val quotes = listOf<Quote>(mock())
        given(characterRepository.getCharacter(name))
            .willReturn(Either.Right(character))
        given(quoteRepository.getQuotes(name))
            .willReturn(Either.Right(quotes))

        val result = useCase(name)

        assertTrue(result.isRight)
        assertEquals(character, result.rightValue.character)
        assertEquals(quotes, result.rightValue.quotes)
    }

    @Test
    fun `invokes the repositories and if one fails the exception is returned`() {
        val name = "Walter White"
        given(characterRepository.getCharacter(name))
            .willReturn(Either.Right(mock()))
        given(quoteRepository.getQuotes(name))
            .willReturn(Either.Left(Exception("Any Error")))

        val result = useCase(name)

        assertTrue(result.isLeft)
        assertEquals("Any Error", result.leftValue.message)
    }
}
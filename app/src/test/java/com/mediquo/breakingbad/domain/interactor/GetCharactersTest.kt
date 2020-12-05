package com.mediquo.breakingbad.domain.interactor

import com.mediquo.breakingbad.domain.repository.CharacterRepository
import com.mediquo.breakingbad.util.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

class GetCharactersTest {

    private val characterRepository = mock<CharacterRepository>()

    private lateinit var useCase: GetCharacters

    @Before
    fun setUp() {
        useCase = GetCharacters(characterRepository)
    }

    @Test
    fun `invokes the character repository`() {
        useCase(Unit)

        verify(characterRepository).getCharacters()
    }
}
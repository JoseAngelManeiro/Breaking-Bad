package com.mediquo.breakingbad.presentation.feature.detail

import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.domain.model.Quote
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DetailModelFactoryTest {

    private lateinit var detailModelFactory: DetailModelFactory

    @Before
    fun setUp() {
        detailModelFactory = DetailModelFactory()
    }

    @Test
    fun `creates the model with the expected values`() {
        val character = createCharacter(
            img = "https://any-image.jpg",
            portrayed = "Bryan Cranston",
            birthday = Calendar.getInstance().time,
            appearance = listOf(1, 2, 3, 4, 5),
            betterCallSaulAppearance = emptyList())
        val quotes = listOf(
            createQuote("Say my name."),
            createQuote("Stay out of my territory.")
        )

        val detailModel = detailModelFactory.createDetailModel(character, quotes)

        assertEquals("https://any-image.jpg", detailModel.imgUrl)
        assertEquals("Bryan Cranston", detailModel.name)
        assertEquals(character.birthday, detailModel.birthday)
        assertEquals(5, detailModel.breakingBadAppearance?.size)
        assertEquals(0, detailModel.betterCallSaulAppearance.size)
    }

    private fun createCharacter(
        img: String,
        portrayed: String,
        birthday: Date,
        appearance: List<Int>?,
        betterCallSaulAppearance: List<Int>
    ): Character {
        return Character(
            charId = 1,
            name = "Any Name",
            birthday = birthday,
            occupation = emptyList(),
            img = img,
            status = "Any Status",
            nickname = "Any Nickname",
            appearance = appearance,
            portrayed = portrayed,
            category = "Any Category",
            betterCallSaulAppearance = betterCallSaulAppearance
        )
    }

    private fun createQuote(
        quote: String
    ): Quote {
        return Quote(
            quoteId = 1,
            quote = quote,
            author = "Any Author",
            series = "Any Series"
        )
    }
}
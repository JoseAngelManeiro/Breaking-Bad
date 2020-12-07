package com.mediquo.breakingbad.presentation.feature.detail

import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.domain.model.Quote

class DetailModelFactory {

    fun createDetailModel(
        character: Character,
        quotes: List<Quote>
    ): DetailModel {
        return DetailModel(
            imgUrl = character.img,
            name = character.portrayed,
            birthday = character.birthday,
            breakingBadAppearance = character.appearance,
            betterCallSaulAppearance = character.betterCallSaulAppearance,
            quotes = quotes.map { it.quote }
        )
    }
}
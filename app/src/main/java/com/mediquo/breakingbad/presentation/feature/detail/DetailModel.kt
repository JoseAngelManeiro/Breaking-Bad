package com.mediquo.breakingbad.presentation.feature.detail

import java.util.*

data class DetailModel(
    val imgUrl: String,
    val name: String,
    val birthday: Date?,
    val breakingBadAppearance: List<Int>?,
    val betterCallSaulAppearance: List<Int>,
    val quotes: List<String>
)
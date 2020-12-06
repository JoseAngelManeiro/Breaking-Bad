package com.mediquo.breakingbad.domain.model

import java.util.*

data class Character (
    val charId: Int,
    val name: String,
    val birthday: Date?,
    val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Int>?,
    val portrayed: String,
    val category: String,
    val betterCallSaulAppearance: List<Int>
)
package com.mediquo.breakingbad.presentation.feature.list

import com.mediquo.breakingbad.domain.model.Character

sealed class RecyclerItem {
    data class Header(val title: String): RecyclerItem()
    data class Item(val character: Character): RecyclerItem()
}
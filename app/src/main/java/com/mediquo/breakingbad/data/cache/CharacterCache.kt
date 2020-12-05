package com.mediquo.breakingbad.data.cache

import com.mediquo.breakingbad.domain.model.Character

class CharacterCache(private val map: MutableMap<String, Character> = mutableMapOf()) {

  fun save(characters: List<Character>) {
    map.clear()
    characters.forEach { map[it.name] = it }
  }

  fun getAll(): List<Character> = map.values.toList()

  fun get(key: String): Character? = map[key]
}

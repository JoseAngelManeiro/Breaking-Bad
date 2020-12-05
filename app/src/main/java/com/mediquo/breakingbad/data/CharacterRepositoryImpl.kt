package com.mediquo.breakingbad.data

import com.mediquo.breakingbad.data.api.ApiClient
import com.mediquo.breakingbad.data.cache.CharacterCache
import com.mediquo.breakingbad.data.entity.CharacterEntity
import com.mediquo.breakingbad.data.mapper.CharacterMapper
import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.domain.repository.CharacterRepository

internal class CharacterRepositoryImpl(
    private val apiClient: ApiClient,
    private val cache: CharacterCache,
    private val mapper: CharacterMapper
): CharacterRepository {

    override fun getCharacters(): Either<Exception, List<Character>> {
        return object : PrefetchLocalData<List<CharacterEntity>, List<Character>>() {
            override fun loadFromLocal() = cache.getAll()

            override fun shouldFetch(data: List<Character>?) = data.isNullOrEmpty()

            override fun loadFromService() = apiClient.getCharacters()

            override fun saveServiceResult(item: List<CharacterEntity>) {
                cache.save(item.map { mapper.toDomain(it) })
            }
        }.load()
    }

    override fun getCharacter(name: String): Either<Exception, Character> {
        return object : PrefetchLocalData<List<CharacterEntity>, Character>() {
            override fun loadFromLocal() = cache.get(name)

            override fun shouldFetch(data: Character?) = (data == null)

            override fun loadFromService() = apiClient.getCharacters()

            override fun saveServiceResult(item: List<CharacterEntity>) {
                cache.save(item.map { mapper.toDomain(it) })
            }
        }.load()
    }
}
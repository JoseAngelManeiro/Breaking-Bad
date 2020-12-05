package com.mediquo.breakingbad.data.api

import com.mediquo.breakingbad.data.entity.CharacterEntity
import com.mediquo.breakingbad.data.entity.QuoteEntity
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("characters")
    fun getCharacters(): Call<List<CharacterEntity>>

    @GET("quotes")
    fun getQuotes(): Call<List<QuoteEntity>>
}
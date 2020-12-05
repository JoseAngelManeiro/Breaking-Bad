package com.mediquo.breakingbad.data.api

import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.exception.NetworkConnectionException
import com.mediquo.breakingbad.domain.exception.ServiceException
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class ApiClient(private val networkHandler: NetworkHandler) {

  private val service: ApiService

  init {
    val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl("https://www.breakingbadapi.com/api/")
      .build()

    service = retrofit.create(ApiService::class.java)
  }

  fun getCharacters() = callService {
    service.getCharacters()
  }

  fun getQuotes() = callService {
    service.getQuotes()
  }

  private fun <T> callService(callback: () -> Call<T>): Either<Exception, T> {
    return if (networkHandler.isConnected()) {
      try {
        val response = callback().execute()
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
          Either.Right(responseBody)
        } else {
          Either.Left(ServiceException())
        }
      } catch (exception: IOException) {
        Either.Left(ServiceException())
      }
    } else {
      Either.Left(NetworkConnectionException())
    }
  }
}

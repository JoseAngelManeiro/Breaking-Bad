package com.mediquo.breakingbad.presentation.common

import com.mediquo.breakingbad.presentation.common.Status.*

data class Resource<out T>(
  private val status: Status,
  private val data: T?,
  private val exception: Exception?
) {
  companion object {
    fun <T> success(data: T): Resource<T> {
      return Resource(SUCCESS, data, null)
    }

    fun <T> error(exception: Exception): Resource<T> {
      return Resource(ERROR, null, exception)
    }

    fun <T> loading(): Resource<T> {
      return Resource(LOADING, null, null)
    }
  }

  fun fold(
    onLoading: () -> Unit = {},
    onError: (Exception) -> Unit = {},
    onSuccess: (T) -> Unit = {}
  ) {
    when (this.status) {
      LOADING -> onLoading()
      ERROR -> onError(this.exception!!)
      SUCCESS -> onSuccess(this.data!!)
    }
  }
}

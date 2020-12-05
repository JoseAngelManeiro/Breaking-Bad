package com.mediquo.breakingbad.domain.exception

class NetworkConnectionException(
  message: String = "No network connection"
) : Exception(message)

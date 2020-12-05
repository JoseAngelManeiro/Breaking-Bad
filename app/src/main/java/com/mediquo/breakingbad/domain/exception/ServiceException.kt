package com.mediquo.breakingbad.domain.exception

class ServiceException(
  message: String = "An error has occurred with the server"
) : Exception(message)

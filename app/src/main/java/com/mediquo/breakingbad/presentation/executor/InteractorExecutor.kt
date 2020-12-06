package com.mediquo.breakingbad.presentation.executor

import com.mediquo.breakingbad.domain.interactor.Interactor

interface InteractorExecutor {

  operator fun <Request, Response> invoke(
    interactor: Interactor<Request, Response>,
    request: Request,
    onError: (Exception) -> Unit = {},
    onSuccess: (Response) -> Unit = {}
  )
}

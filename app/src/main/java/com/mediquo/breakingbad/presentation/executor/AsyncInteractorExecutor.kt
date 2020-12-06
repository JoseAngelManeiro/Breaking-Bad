package com.mediquo.breakingbad.presentation.executor

import com.mediquo.breakingbad.domain.interactor.Interactor

class AsyncInteractorExecutor(
    private val runOnMainThread: Runner,
    private val runOnBgThread: Runner
) : InteractorExecutor {

  override fun <Request, Response> invoke(
    interactor: Interactor<Request, Response>,
    request: Request,
    onError: (Exception) -> Unit,
    onSuccess: (Response) -> Unit
  ) {
    runOnBgThread {
      val response = interactor(request)
      runOnMainThread {
        if (response.isRight) {
          onSuccess(response.rightValue)
        } else {
          onError(response.leftValue)
        }
      }
    }
  }
}

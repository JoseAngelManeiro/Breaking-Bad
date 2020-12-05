package com.mediquo.breakingbad.domain.interactor

import com.mediquo.breakingbad.domain.Either

interface Interactor<Request, Response> {

  operator fun invoke(request: Request): Either<Exception, Response>
}

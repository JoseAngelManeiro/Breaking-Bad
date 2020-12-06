package com.mediquo.breakingbad.presentation.executor

interface Runner {
  operator fun invoke(c: () -> Unit)
}

package com.mediquo.breakingbad.presentation.injection

import com.mediquo.breakingbad.data.CharacterRepositoryImpl
import com.mediquo.breakingbad.data.QuoteRepositoryImpl
import com.mediquo.breakingbad.data.api.ApiClient
import com.mediquo.breakingbad.data.api.NetworkHandler
import com.mediquo.breakingbad.data.cache.CharacterCache
import com.mediquo.breakingbad.data.cache.QuoteCache
import com.mediquo.breakingbad.data.mapper.CharacterMapper
import com.mediquo.breakingbad.data.mapper.QuoteMapper
import com.mediquo.breakingbad.domain.repository.CharacterRepository
import com.mediquo.breakingbad.domain.repository.QuoteRepository
import com.mediquo.breakingbad.presentation.executor.AsyncInteractorExecutor
import com.mediquo.breakingbad.presentation.executor.BackgroundRunner
import com.mediquo.breakingbad.presentation.executor.InteractorExecutor
import com.mediquo.breakingbad.presentation.executor.MainRunner
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

  single {
    ApiClient(networkHandler = NetworkHandler(androidContext()))
  }

  // Mappers factories
  factory { CharacterMapper() }
  factory { QuoteMapper() }

  // Cache factories
  factory { CharacterCache() }
  factory { QuoteCache() }

  // Repositories
  single<CharacterRepository> {
    CharacterRepositoryImpl(apiClient = get(), mapper = get(), cache = get())
  }
  single<QuoteRepository> {
    QuoteRepositoryImpl(apiClient = get(), mapper = get(), cache = get())
  }

  // Executor
  single<InteractorExecutor> {
    AsyncInteractorExecutor(runOnBgThread = BackgroundRunner(), runOnMainThread = MainRunner())
  }
}

package com.mediquo.breakingbad.presentation.injection

import com.mediquo.breakingbad.domain.interactor.GetCharacterDetail
import com.mediquo.breakingbad.presentation.feature.detail.DetailModelFactory
import com.mediquo.breakingbad.presentation.feature.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {

  factory { GetCharacterDetail(characterRepository = get(), quoteRepository = get()) }
  factory { DetailModelFactory() }

  viewModel {
    DetailViewModel(
      executor = get(),
      getCharacterDetail = get(),
      factory = get()
    )
  }
}
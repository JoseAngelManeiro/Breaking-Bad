package com.mediquo.breakingbad.presentation.injection

import com.mediquo.breakingbad.domain.interactor.GetCharacters
import com.mediquo.breakingbad.presentation.feature.list.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listModule = module {

  factory { GetCharacters(characterRepository = get()) }

  viewModel {
    ListViewModel(
      executor = get(),
      getCharacters = get()
    )
  }
}
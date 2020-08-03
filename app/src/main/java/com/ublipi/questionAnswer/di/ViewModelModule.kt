package com.ublipi.questionAnswer.di

import com.ublipi.questionAnswer.ui.QaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { QaViewModel(get()) }
}
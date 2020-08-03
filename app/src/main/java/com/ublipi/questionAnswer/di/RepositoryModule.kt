package com.ublipi.questionAnswer.di

import com.ublipi.questionAnswer.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        AppRepository(get())
    }
}
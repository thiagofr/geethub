package com.thiagofr.geethub.di

import com.thiagofr.geethub.presenter.UiFragmentManagerHolder
import org.koin.dsl.module

val fragmentManagerModule = module {
    single {
        UiFragmentManagerHolder()
    }
}
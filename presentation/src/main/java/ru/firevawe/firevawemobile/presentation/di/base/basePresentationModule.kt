package ru.firevawe.firevawemobile.presentation.di.base

import org.koin.dsl.module
import ru.firevawe.firevawemobile.presentation.di.converterModule

val basePresentationModule = module {
    includes(converterModule)
}
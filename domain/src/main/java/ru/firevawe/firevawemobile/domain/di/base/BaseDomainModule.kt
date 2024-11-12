package ru.firevawe.firevawemobile.domain.di.base

import org.koin.dsl.module
import ru.firevawe.firevawemobile.domain.di.useCaseModule

val baseDomainModule = module {
    includes(useCaseModule)
}
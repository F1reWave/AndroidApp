package ru.firevawe.firevawemobile.data.di.base

import org.koin.dsl.module
import ru.firevawe.firevawemobile.data.di.converterModule
import ru.firevawe.firevawemobile.data.di.dbBindModule

val baseDataModule = module {
    includes(dbBindModule, converterModule)
}
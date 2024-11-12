package ru.firevawe.firevawemobile.data.di.base

import org.koin.dsl.module
import ru.firevawe.firevawemobile.data.di.converterDBModule
import ru.firevawe.firevawemobile.data.di.converterNetworkModule
import ru.firevawe.firevawemobile.data.di.dbBindModule
import ru.firevawe.firevawemobile.data.di.networkBindModule

val baseDataModule = module {
    includes(
        dbBindModule,
        converterDBModule,
        networkBindModule,
        converterNetworkModule,
    )
}
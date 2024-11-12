package ru.firevawe.firevawemobile.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.firevawe.firevawemobile.data.converters.db.ChatDBConverter
import ru.firevawe.firevawemobile.data.converters.db.DateTimeConverter
import ru.firevawe.firevawemobile.data.converters.db.MessageDBConverter
import ru.firevawe.firevawemobile.data.converters.db.TimeConverter
import ru.firevawe.firevawemobile.data.converters.db.UserDBConverter
import ru.firevawe.firevawemobile.data.converters.network.ChatNetworkConverter
import ru.firevawe.firevawemobile.data.converters.network.MessageNetworkConverter
import ru.firevawe.firevawemobile.data.converters.network.UserNetworkConverter

internal val converterDBModule = module {
    singleOf(::ChatDBConverter)
    singleOf(::DateTimeConverter)
    singleOf(::MessageDBConverter)
    singleOf(::TimeConverter)
    singleOf(::UserDBConverter)
}

internal val converterNetworkModule = module {
    singleOf(::ChatNetworkConverter)
    singleOf(::MessageNetworkConverter)
    singleOf(::UserNetworkConverter)
}
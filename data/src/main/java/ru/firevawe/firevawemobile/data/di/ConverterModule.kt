package ru.firevawe.firevawemobile.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.firevawe.firevawemobile.data.converters.ChatConverter
import ru.firevawe.firevawemobile.data.converters.DateTimeConverter
import ru.firevawe.firevawemobile.data.converters.MessageConverter
import ru.firevawe.firevawemobile.data.converters.TimeConverter
import ru.firevawe.firevawemobile.data.converters.UserConverter

internal val converterModule = module {
    singleOf(::ChatConverter)
    singleOf(::DateTimeConverter)
    singleOf(::MessageConverter)
    singleOf(::TimeConverter)
    singleOf(::UserConverter)
}
package ru.firevawe.firevawemobile.presentation.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.firevawe.firevawemobile.presentation.components.converter.ChatConverter
import ru.firevawe.firevawemobile.presentation.components.converter.MessageConverter
import ru.firevawe.firevawemobile.presentation.components.converter.UserConverter

internal val converterModule = module {
    singleOf(::ChatConverter)
    singleOf(::MessageConverter)
    singleOf(::UserConverter)
}
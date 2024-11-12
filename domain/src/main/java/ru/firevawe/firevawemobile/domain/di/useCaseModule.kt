package ru.firevawe.firevawemobile.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.firevawe.firevawemobile.usecases.CreateChatUseCase
import ru.firevawe.firevawemobile.usecases.CreateMessageUseCase
import ru.firevawe.firevawemobile.usecases.CreateUserUseCase
import ru.firevawe.firevawemobile.usecases.DeleteChatUseCase
import ru.firevawe.firevawemobile.usecases.DeleteMessageUseCase
import ru.firevawe.firevawemobile.usecases.DeleteUserUseCase
import ru.firevawe.firevawemobile.usecases.GetAllChatsForUserUseCase
import ru.firevawe.firevawemobile.usecases.GetMessagesForChatUseCase
import ru.firevawe.firevawemobile.usecases.GetUserByUsernameUseCase
import ru.firevawe.firevawemobile.usecases.UpdateMessageUseCase
import ru.firevawe.firevawemobile.usecases.UpdateUserUseCase

internal val useCaseModule = module {
    singleOf(::CreateChatUseCase)
    singleOf(::CreateMessageUseCase)
    singleOf(::CreateUserUseCase)
    singleOf(::DeleteChatUseCase)
    singleOf(::DeleteMessageUseCase)
    singleOf(::DeleteUserUseCase)
    singleOf(::GetAllChatsForUserUseCase)
    singleOf(::GetMessagesForChatUseCase)
    singleOf(::GetUserByUsernameUseCase)
    singleOf(::UpdateMessageUseCase)
    singleOf(::UpdateUserUseCase)
}
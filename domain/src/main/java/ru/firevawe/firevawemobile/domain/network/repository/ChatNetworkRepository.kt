package ru.firevawe.firevawemobile.domain.network.repository

import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel


interface ChatNetworkRepository {
    // Получение всех чатов
    suspend fun getAllChats(): List<ChatDomainModel>


    // Получение всех чатов для пользователя
    suspend fun getAllChatsForUser(username: String): List<ChatDomainModel>

    // Создание нового чата
    suspend fun createChat(chat: ChatDomainModel)

}
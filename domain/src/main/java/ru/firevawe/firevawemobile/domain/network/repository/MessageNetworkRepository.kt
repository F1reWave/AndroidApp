package ru.firevawe.firevawemobile.domain.network.repository

import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel

interface MessageNetworkRepository {
    // Получение всех сообщений из чата
    suspend fun getMessagesForChat(chatId: String): List<MessageDomainModel>

    // Создание нового сообщения
    suspend fun createMessage(messageDomainModel: MessageDomainModel)

    // Обновление сообщения
    suspend fun updateMessage(messageDomainModel: MessageDomainModel)

    // Удаление сообщения
    suspend fun deleteMessage(messageDomainModel: MessageDomainModel)
}
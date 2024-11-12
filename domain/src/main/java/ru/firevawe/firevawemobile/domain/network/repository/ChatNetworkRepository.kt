package ru.firevawe.firevawemobile.domain.network.repository

import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel


interface ChatNetworkRepository {
    suspend fun getAllChatsForUser(username: String): List<ChatDomainModel>
    suspend fun createChat(chat: ChatDomainModel)
    suspend fun deleteChat(chat: ChatDomainModel)
}
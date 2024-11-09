package ru.firevawe.firevawemobile.domain.database.repository

import kotlinx.coroutines.flow.Flow
import ru.firevawe.firevawemobile.domain.database.domain_model.MessageDTO

interface MessageRepository {
    suspend fun addMessage(messageDTO: MessageDTO)
    suspend fun updateMessage(messageDTO: MessageDTO)
    suspend fun deleteMessage(messageDTO: MessageDTO)
    fun getMessagesForChat(chatId: String): Flow<List<MessageDTO>>
}
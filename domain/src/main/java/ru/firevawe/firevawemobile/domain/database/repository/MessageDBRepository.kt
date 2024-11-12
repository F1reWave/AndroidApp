package ru.firevawe.firevawemobile.domain.database.repository

import kotlinx.coroutines.flow.Flow
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel

interface MessageDBRepository {
    suspend fun addMessage(messageDomainModel: MessageDomainModel)
    suspend fun updateMessage(messageDomainModel: MessageDomainModel)
    suspend fun deleteMessage(messageDomainModel: MessageDomainModel)
    fun getMessagesForChat(chatId: String): Flow<List<MessageDomainModel>>
}
package ru.firevawe.firevawemobile.domain.database.repository

import kotlinx.coroutines.flow.Flow
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel

interface ChatDBRepository {
    suspend fun addChat(chatDomainModel: ChatDomainModel)
    suspend fun getChatById(chatId: String): ChatDomainModel?
    fun getAllChats(): Flow<List<ChatDomainModel>>
}
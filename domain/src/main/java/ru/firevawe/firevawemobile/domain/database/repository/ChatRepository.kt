package ru.firevawe.firevawemobile.domain.database.repository

import kotlinx.coroutines.flow.Flow
import ru.firevawe.firevawemobile.domain.database.domain_model.ChatDTO

interface ChatRepository {
    suspend fun addChat(chatDTO: ChatDTO)
    suspend fun getChatById(chatId: String): ChatDTO?
    fun getAllChats(): Flow<List<ChatDTO>>
}
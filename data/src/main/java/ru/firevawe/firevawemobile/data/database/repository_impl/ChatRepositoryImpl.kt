package ru.firevawe.firevawemobile.data.database.repository_impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.firevawe.firevawemobile.data.converters.ChatConverter
import ru.firevawe.firevawemobile.data.database.AppDatabase
import ru.firevawe.firevawemobile.data.database.dao.ChatDao
import ru.firevawe.firevawemobile.domain.database.domain_model.ChatDTO
import ru.firevawe.firevawemobile.domain.database.repository.ChatRepository

internal class ChatRepositoryImpl(
    private val db: AppDatabase,
    private val chatConverter: ChatConverter
) : ChatRepository {
    private val chatDao: ChatDao = db.chatDao()
    override suspend fun addChat(chatDTO: ChatDTO) =
        chatDao.insert(chatConverter.convertBA(chatDTO))

    override suspend fun getChatById(chatId: String): ChatDTO? =
        chatDao.getChatById(chatId)?.let { chatConverter.convertAB(it) }

    override fun getAllChats(): Flow<List<ChatDTO>> =
        chatDao.getAllAsFlow().map { chatConverter.convertABList(it) }
}

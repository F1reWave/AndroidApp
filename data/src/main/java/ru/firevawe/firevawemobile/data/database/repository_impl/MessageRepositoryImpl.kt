package ru.firevawe.firevawemobile.data.database.repository_impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.firevawe.firevawemobile.data.converters.MessageConverter
import ru.firevawe.firevawemobile.data.database.AppDatabase
import ru.firevawe.firevawemobile.data.database.dao.MessageDao
import ru.firevawe.firevawemobile.domain.database.domain_model.MessageDTO
import ru.firevawe.firevawemobile.domain.database.repository.MessageRepository

internal class MessageRepositoryImpl(
    private val db: AppDatabase,
    private val messageConverter: MessageConverter
) : MessageRepository {
    private val messageDao: MessageDao = db.messageDao()
    override suspend fun addMessage(messageDTO: MessageDTO) =
        messageDao.insert(messageConverter.convertBA(messageDTO))

    override suspend fun updateMessage(messageDTO: MessageDTO) =
        messageDao.update(messageConverter.convertBA(messageDTO))

    override suspend fun deleteMessage(messageDTO: MessageDTO) =
        messageDao.delete(messageConverter.convertBA(messageDTO))

    override fun getMessagesForChat(chatId: String): Flow<List<MessageDTO>> =
        messageDao.getMessagesForChat(chatId).map { messageConverter.convertABList(it) }
}



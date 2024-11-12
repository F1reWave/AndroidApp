package ru.firevawe.firevawemobile.data.database.repository_impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.firevawe.firevawemobile.data.converters.db.MessageDBConverter
import ru.firevawe.firevawemobile.data.database.AppDatabase
import ru.firevawe.firevawemobile.data.database.dao.MessageDao
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel
import ru.firevawe.firevawemobile.domain.database.repository.MessageDBRepository

internal class MessageDBRepositoryImpl(
    private val db: AppDatabase,
    private val messageDBConverter: MessageDBConverter
) : MessageDBRepository {
    private val messageDao: MessageDao = db.messageDao()
    override suspend fun addMessage(messageDomainModel: MessageDomainModel) =
        messageDao.insert(messageDBConverter.convertBA(messageDomainModel))

    override suspend fun updateMessage(messageDomainModel: MessageDomainModel) =
        messageDao.update(messageDBConverter.convertBA(messageDomainModel))

    override suspend fun deleteMessage(messageDomainModel: MessageDomainModel) =
        messageDao.delete(messageDBConverter.convertBA(messageDomainModel))

    override fun getMessagesForChat(chatId: String): Flow<List<MessageDomainModel>> =
        messageDao.getMessagesForChat(chatId).map { messageDBConverter.convertABList(it) }
}



package ru.firevawe.firevawemobile.data.database.repository_impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.firevawe.firevawemobile.data.converters.db.ChatDBConverter
import ru.firevawe.firevawemobile.data.database.AppDatabase
import ru.firevawe.firevawemobile.data.database.dao.ChatDao
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel
import ru.firevawe.firevawemobile.domain.database.repository.ChatDBRepository

internal class ChatDBRepositoryImpl(
    private val db: AppDatabase,
    private val chatDBConverter: ChatDBConverter
) : ChatDBRepository {
    private val chatDao: ChatDao = db.chatDao()
    override suspend fun addChat(chatDomainModel: ChatDomainModel) =
        chatDao.insert(chatDBConverter.convertBA(chatDomainModel))

    override suspend fun getChatById(chatId: String): ChatDomainModel? =
        chatDao.getChatById(chatId)?.let { chatDBConverter.convertAB(it) }

    override fun getAllChats(): Flow<List<ChatDomainModel>> =
        chatDao.getAllAsFlow().map { chatDBConverter.convertABList(it) }

    override suspend fun deleteChat(chatDomainModel: ChatDomainModel) {
        chatDao.delete(chatDBConverter.convertBA(chatDomainModel))
    }
}

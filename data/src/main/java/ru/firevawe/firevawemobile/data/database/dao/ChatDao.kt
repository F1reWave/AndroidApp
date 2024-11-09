package ru.firevawe.firevawemobile.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.firevawe.firevawemobile.data.database.dao.parent.DataAccessObject
import ru.firevawe.firevawemobile.data.database.dao.parent.implementation.FlowGetAllImplementation
import ru.firevawe.firevawemobile.data.database.entity.ChatEntity

@Dao
internal interface ChatDao : DataAccessObject<ChatEntity>,
    FlowGetAllImplementation<ChatEntity> {
    @Query("select * from chats")
    override fun getAllAsFlow(): Flow<List<ChatEntity>>

    @Query("SELECT * FROM chats WHERE chatId = :chatId")
    suspend fun getChatById(chatId: String): ChatEntity?
}

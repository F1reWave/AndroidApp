package ru.firevawe.firevawemobile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.firevawe.firevawemobile.data.database.dao.parent.DataAccessObject
import ru.firevawe.firevawemobile.data.database.dao.parent.implementation.FlowGetAllImplementation
import ru.firevawe.firevawemobile.data.database.entity.MessageEntity

@Dao
internal interface MessageDao : DataAccessObject<MessageEntity>,
    FlowGetAllImplementation<MessageEntity> {
    @Query("select * from messages")
    override fun getAllAsFlow(): Flow<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC")
    fun getMessagesForChat(chatId: String): Flow<List<MessageEntity>>
}

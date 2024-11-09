package ru.firevawe.firevawemobile.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
internal data class ChatEntity(
    @PrimaryKey val chatId: String,
    val isGroupChat: Boolean,
    val chatName: String? = null // Имя чата для группового чата
)

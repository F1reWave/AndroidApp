package ru.firevawe.firevawemobile.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["username"],
            childColumns = ["senderId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ChatEntity::class,
            parentColumns = ["chatId"],
            childColumns = ["chatId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("senderId"), Index("chatId")]
)
internal data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val messageId: Int = 0,
    val chatId: String,
    val senderId: String,
    val content: String,
    val timestamp: Long
)

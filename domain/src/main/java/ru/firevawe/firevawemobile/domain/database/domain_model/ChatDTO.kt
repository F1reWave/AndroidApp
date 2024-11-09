package ru.firevawe.firevawemobile.domain.database.domain_model

data class ChatDTO(
    val chatId: String,
    val isGroupChat: Boolean,
    val chatName: String? = null // Имя чата для группового чата
)
package ru.firevawe.firevawemobile.domain.domain_model

data class ChatDomainModel(
    val chatId: String,
    val isGroupChat: Boolean,
    val chatName: String? = null // Имя чата для группового чата
)
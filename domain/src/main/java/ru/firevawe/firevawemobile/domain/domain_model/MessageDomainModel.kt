package ru.firevawe.firevawemobile.domain.domain_model

data class MessageDomainModel(
    val messageId: Int = 0,
    val chatId: String,
    val senderId: String,
    val content: String,
    val timestamp: Long
)
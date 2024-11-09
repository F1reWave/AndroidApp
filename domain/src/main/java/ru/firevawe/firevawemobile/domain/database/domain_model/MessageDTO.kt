package ru.firevawe.firevawemobile.domain.database.domain_model

data class MessageDTO(
    val messageId: Int = 0,
    val chatId: String,
    val senderId: String,
    val content: String,
    val timestamp: Long
) {

}
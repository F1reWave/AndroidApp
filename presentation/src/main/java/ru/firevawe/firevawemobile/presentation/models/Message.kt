package ru.firevawe.firevawemobile.presentation.models

internal data class Message (
    val messageId: Int = 0,
    val chatId: String,
    val senderId: String,
    val content: String,
    val timestamp: Long
)
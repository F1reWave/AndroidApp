package ru.firevawe.firevawemobile.data.network.response_model

import kotlinx.serialization.Serializable

@Serializable
internal data class MessageResponse(
    val messageId: Int,
    val chatId: String,
    val senderId: String,
    val content: String,
    val timestamp: Long
)
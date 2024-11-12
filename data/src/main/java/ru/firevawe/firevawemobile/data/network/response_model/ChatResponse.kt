package ru.firevawe.firevawemobile.data.network.response_model

import kotlinx.serialization.Serializable


@Serializable
internal data class ChatResponse(
    val chatId: String,
    val isGroupChat: Boolean,
    val chatName: String? = null
)
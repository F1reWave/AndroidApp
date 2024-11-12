package ru.firevawe.firevawemobile.presentation.models

data class Chat(
    val chatId: String,
    val isGroupChat: Boolean,
    val chatName: String? = null
)
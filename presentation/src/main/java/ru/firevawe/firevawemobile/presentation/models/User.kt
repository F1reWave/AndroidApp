package ru.firevawe.firevawemobile.presentation.models

data class User(
    val username: String,
    val name: String,
    val avatarUrl: String? = null
)

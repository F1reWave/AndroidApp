package ru.firevawe.firevawemobile.domain.database.domain_model

data class UserDTO(
    val userId: String,
    val username: String,
    val avatarUrl: String? = null
)
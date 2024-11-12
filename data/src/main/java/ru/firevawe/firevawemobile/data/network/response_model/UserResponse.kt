package ru.firevawe.firevawemobile.data.network.response_model

import kotlinx.serialization.Serializable

@Serializable
internal data class UserResponse(
    val username: String,
    val name: String,
    val avatarUrl: String? = null
)
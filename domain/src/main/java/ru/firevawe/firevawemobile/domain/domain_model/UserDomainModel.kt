package ru.firevawe.firevawemobile.domain.domain_model

data class UserDomainModel(
    val username: String,
    val name: String,
    val avatarUrl: String? = null
)
package ru.firevawe.firevawemobile.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
internal data class UserEntity(
    @PrimaryKey val userId: String,
    val username: String,
    val avatarUrl: String? = null
)

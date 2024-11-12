package ru.firevawe.firevawemobile.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
internal data class UserEntity(
    @PrimaryKey val username: String,
    val name: String,
    val avatarUrl: String? = null
)

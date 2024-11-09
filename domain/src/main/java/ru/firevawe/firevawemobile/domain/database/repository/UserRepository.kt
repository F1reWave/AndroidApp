package ru.firevawe.firevawemobile.domain.database.repository

import kotlinx.coroutines.flow.Flow
import ru.firevawe.firevawemobile.domain.database.domain_model.UserDTO

interface UserRepository {
    suspend fun addUser(userDTO: UserDTO)
    suspend fun updateUser(userDTO: UserDTO)
    suspend fun getUserById(userId: String): UserDTO?
    fun getAllUsers(): Flow<List<UserDTO>>
}


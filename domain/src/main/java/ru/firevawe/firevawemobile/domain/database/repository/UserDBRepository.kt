package ru.firevawe.firevawemobile.domain.database.repository

import kotlinx.coroutines.flow.Flow
import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel

interface UserDBRepository {
    suspend fun addUser(userDomainModel: UserDomainModel)
    suspend fun updateUser(userDomainModel: UserDomainModel)
    suspend fun getUserByUsername(userId: String): UserDomainModel?
    fun getAllUsers(): Flow<List<UserDomainModel>>
}

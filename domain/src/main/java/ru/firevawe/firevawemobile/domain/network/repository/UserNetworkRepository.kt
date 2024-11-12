package ru.firevawe.firevawemobile.domain.network.repository

import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel

interface UserNetworkRepository {
    // Получение пользователя по имени
    suspend fun getUserByUsername(username: String): UserDomainModel

    // Создание нового пользователя
    suspend fun createUser(userDomainModel: UserDomainModel)

    // Обновление данных пользователя
    suspend fun updateUser(userDomainModel: UserDomainModel)


    // Удаление пользователя по имени
    suspend fun deleteUser(username: String)
}
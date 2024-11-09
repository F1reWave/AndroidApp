package ru.firevawe.firevawemobile.data.database.repository_impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.firevawe.firevawemobile.data.converters.UserConverter
import ru.firevawe.firevawemobile.data.database.AppDatabase
import ru.firevawe.firevawemobile.data.database.dao.UserDao
import ru.firevawe.firevawemobile.domain.database.domain_model.UserDTO
import ru.firevawe.firevawemobile.domain.database.repository.UserRepository


internal class UserRepositoryImpl(
    private val db: AppDatabase,
    private val userConverter: UserConverter
) : UserRepository {
    private val userDao: UserDao = db.userDao()
    override suspend fun addUser(userDTO: UserDTO) =
        userDao.insert(userConverter.convertBA(userDTO))

    override suspend fun updateUser(userDTO: UserDTO) =
        userDao.update(userConverter.convertBA(userDTO))

    override suspend fun getUserById(userId: String): UserDTO? =
        userDao.getUserById(userId)?.let { userConverter.convertAB(it) }

    override fun getAllUsers(): Flow<List<UserDTO>> = userDao.getAllAsFlow().map { userList ->
        userConverter.convertABList(userList)
    }
}
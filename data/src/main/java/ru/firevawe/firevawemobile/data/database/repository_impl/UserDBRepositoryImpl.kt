package ru.firevawe.firevawemobile.data.database.repository_impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.firevawe.firevawemobile.data.converters.db.UserDBConverter
import ru.firevawe.firevawemobile.data.database.AppDatabase
import ru.firevawe.firevawemobile.data.database.dao.UserDao
import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel
import ru.firevawe.firevawemobile.domain.database.repository.UserDBRepository


internal class UserDBRepositoryImpl(
    private val db: AppDatabase,
    private val userDBConverter: UserDBConverter
) : UserDBRepository {
    private val userDao: UserDao = db.userDao()
    override suspend fun addUser(userDomainModel: UserDomainModel) =
        userDao.insert(userDBConverter.convertBA(userDomainModel))

    override suspend fun updateUser(userDomainModel: UserDomainModel) =
        userDao.update(userDBConverter.convertBA(userDomainModel))

    override suspend fun getUserByUsername(username: String): UserDomainModel? =
        userDao.getUserByUsername(username)?.let { userDBConverter.convertAB(it) }

    override fun getAllUsers(): Flow<List<UserDomainModel>> = userDao.getAllAsFlow().map { userList ->
        userDBConverter.convertABList(userList)
    }
}
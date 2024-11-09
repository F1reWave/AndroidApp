package ru.firevawe.firevawemobile.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.firevawe.firevawemobile.data.database.dao.parent.DataAccessObject
import ru.firevawe.firevawemobile.data.database.dao.parent.implementation.FlowGetAllImplementation
import ru.firevawe.firevawemobile.data.database.entity.UserEntity

@Dao
internal interface UserDao : DataAccessObject<UserEntity>,
    FlowGetAllImplementation<UserEntity> {
    @Query("select * from users")
    override fun getAllAsFlow(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserById(userId: String): UserEntity?
}

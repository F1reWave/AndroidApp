package ru.firevawe.firevawemobile.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.firevawe.firevawemobile.data.database.dao.ChatDao
import ru.firevawe.firevawemobile.data.database.dao.MessageDao
import ru.firevawe.firevawemobile.data.database.dao.UserDao
import ru.firevawe.firevawemobile.data.database.entity.ChatEntity
import ru.firevawe.firevawemobile.data.database.entity.MessageEntity
import ru.firevawe.firevawemobile.data.database.entity.UserEntity

@Database(entities = [UserEntity::class, ChatEntity::class, MessageEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao
}

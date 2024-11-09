package ru.firevawe.firevawemobile.data.di

import androidx.room.Room
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.firevawe.firevawemobile.data.database.AppDatabase
import ru.firevawe.firevawemobile.data.database.AppDatabaseMigrationManager
import ru.firevawe.firevawemobile.data.database.DBInfo
import ru.firevawe.firevawemobile.data.database.repository_impl.ChatRepositoryImpl
import ru.firevawe.firevawemobile.data.database.repository_impl.MessageRepositoryImpl
import ru.firevawe.firevawemobile.data.database.repository_impl.UserRepositoryImpl
import ru.firevawe.firevawemobile.domain.database.repository.ChatRepository
import ru.firevawe.firevawemobile.domain.database.repository.MessageRepository
import ru.firevawe.firevawemobile.domain.database.repository.UserRepository

internal val dbBindModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            context = get(), klass = AppDatabase::class.java, name = DBInfo.NAME
        ).addMigrations(*AppDatabaseMigrationManager().migrations()).build()
    }
    singleOf(::ChatRepositoryImpl) {
        bind<ChatRepository>()
    }
    singleOf(::MessageRepositoryImpl) {
        bind<MessageRepository>()
    }
    singleOf(::UserRepositoryImpl) {
        bind<UserRepository>()
    }
}

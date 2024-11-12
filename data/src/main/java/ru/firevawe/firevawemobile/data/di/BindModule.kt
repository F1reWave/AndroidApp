package ru.firevawe.firevawemobile.data.di

import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.firevawe.firevawemobile.data.database.AppDatabase
import ru.firevawe.firevawemobile.data.database.AppDatabaseMigrationManager
import ru.firevawe.firevawemobile.data.database.DBInfo
import ru.firevawe.firevawemobile.data.database.repository_impl.ChatDBRepositoryImpl
import ru.firevawe.firevawemobile.data.database.repository_impl.MessageDBRepositoryImpl
import ru.firevawe.firevawemobile.data.database.repository_impl.UserDBRepositoryImpl
import ru.firevawe.firevawemobile.data.network.repositoryImpl.ChatNetworkRepositoryImpl
import ru.firevawe.firevawemobile.data.network.repositoryImpl.MessageNetworkRepositoryImpl
import ru.firevawe.firevawemobile.data.network.repositoryImpl.UserNetworkRepositoryImpl
import ru.firevawe.firevawemobile.domain.database.repository.ChatDBRepository
import ru.firevawe.firevawemobile.domain.database.repository.MessageDBRepository
import ru.firevawe.firevawemobile.domain.database.repository.UserDBRepository
import ru.firevawe.firevawemobile.domain.network.repository.ChatNetworkRepository
import ru.firevawe.firevawemobile.domain.network.repository.MessageNetworkRepository
import ru.firevawe.firevawemobile.domain.network.repository.UserNetworkRepository

internal val dbBindModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            context = get(), klass = AppDatabase::class.java, name = DBInfo.NAME
        ).addMigrations(*AppDatabaseMigrationManager().migrations()).build()
    }
    singleOf(::ChatDBRepositoryImpl) {
        bind<ChatDBRepository>()
    }
    singleOf(::MessageDBRepositoryImpl) {
        bind<MessageDBRepository>()
    }
    singleOf(::UserDBRepositoryImpl) {
        bind<UserDBRepository>()
    }
}

internal val networkBindModule = module {
    single<HttpClient> {
        HttpClient {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                url("http://192.168.0.103:8080/")
            }
        }
    }
    singleOf(::ChatNetworkRepositoryImpl) {
        bind<ChatNetworkRepository>()
    }
    singleOf(::MessageNetworkRepositoryImpl) {
        bind<MessageNetworkRepository>()
    }
    singleOf(::UserNetworkRepositoryImpl) {
        bind<UserNetworkRepository>()
    }
}
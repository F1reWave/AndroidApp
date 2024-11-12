package ru.firevawe.firevawemobile.data.network.repositoryImpl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.firevawe.firevawemobile.data.converters.network.UserNetworkConverter
import ru.firevawe.firevawemobile.data.network.response_model.UserResponse
import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.UserNetworkRepository

internal class UserNetworkRepositoryImpl(
    private val client: HttpClient,
    private val userNetworkConverter: UserNetworkConverter
) : UserNetworkRepository {

    // Получение пользователя по имени
    override suspend fun getUserByUsername(username: String): UserDomainModel =
        userNetworkConverter.convertAB(client.get("/users/$username").body<UserResponse>())

    // Создание нового пользователя
    override suspend fun createUser(userDomainModel: UserDomainModel) {
        client.post("/users") {
            contentType(ContentType.Application.Json)
            setBody(userNetworkConverter.convertBA(userDomainModel))
        }
    }

    // Обновление данных пользователя
    override suspend fun updateUser(userDomainModel: UserDomainModel) {
        client.put("/users/${userDomainModel.username}") {
            contentType(ContentType.Application.Json)
            setBody(userNetworkConverter.convertBA(userDomainModel))
        }
    }

    // Получение всех пользователей
    override suspend fun getAllUsers(): List<UserDomainModel> =
        userNetworkConverter.convertABList(client.get("/users").body<List<UserResponse>>())


    // Удаление пользователя по имени
    override suspend fun deleteUser(username: String) {
        client.delete("/users/$username")
    }
}

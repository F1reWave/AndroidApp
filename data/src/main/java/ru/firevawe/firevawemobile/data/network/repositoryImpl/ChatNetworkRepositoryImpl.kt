package ru.firevawe.firevawemobile.data.network.repositoryImpl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.firevawe.firevawemobile.data.converters.network.ChatNetworkConverter
import ru.firevawe.firevawemobile.data.network.response_model.ChatResponse
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.ChatNetworkRepository

internal class ChatNetworkRepositoryImpl(
    private val chatNetworkConverter: ChatNetworkConverter,
    private val client: HttpClient
) : ChatNetworkRepository {
    // Получение всех чатов
    override suspend fun getAllChats(): List<ChatDomainModel> =
        chatNetworkConverter.convertABList(client.get("/chats").body<List<ChatResponse>>())

    // Получение всех чатов для пользователя
    override suspend fun getAllChatsForUser(username: String): List<ChatDomainModel> =
        chatNetworkConverter.convertABList(
            client.get("/chats/user/$username").body<List<ChatResponse>>()
        )


    // Создание нового чата
    override suspend fun createChat(chat: ChatDomainModel) {
        client.post("/chats") {
            contentType(ContentType.Application.Json)
            setBody(chatNetworkConverter.convertBA(chat))
        }
    }

}
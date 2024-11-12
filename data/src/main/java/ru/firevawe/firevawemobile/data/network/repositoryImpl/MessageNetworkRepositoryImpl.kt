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
import ru.firevawe.firevawemobile.data.converters.network.MessageNetworkConverter
import ru.firevawe.firevawemobile.data.network.response_model.MessageResponse
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.MessageNetworkRepository

internal class MessageNetworkRepositoryImpl(
    private val client: HttpClient,
    private val messageNetworkConverter: MessageNetworkConverter
) : MessageNetworkRepository {

    // Получение всех сообщений из чата
    override suspend fun getMessagesForChat(chatId: String): List<MessageDomainModel> =
        messageNetworkConverter.convertABList(
            client.get("/messages/$chatId").body<List<MessageResponse>>()
        )

    // Создание нового сообщения
    override suspend fun createMessage(messageDomainModel: MessageDomainModel) {
        client.post("/messages") {
            contentType(ContentType.Application.Json)
            setBody(messageNetworkConverter.convertBA(messageDomainModel))
        }
    }

    // Обновление сообщения
    override suspend fun updateMessage(messageDomainModel: MessageDomainModel) {
        client.put("/messages/${messageDomainModel.messageId}") {
            contentType(ContentType.Application.Json)
            setBody(messageNetworkConverter.convertBA(messageDomainModel))
        }
    }

    // Удаление сообщения
    override suspend fun deleteMessage(messageDomainModel: MessageDomainModel) {
        client.delete("/messages/${messageDomainModel.messageId}")
    }
}

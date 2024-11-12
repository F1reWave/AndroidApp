package ru.firevawe.firevawemobile.data.converters.network

import ru.firevawe.firevawemobile.data.network.response_model.MessageResponse
import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel

internal class MessageNetworkConverter : BaseConverter<MessageResponse, MessageDomainModel> {
    override fun convertAB(a: MessageResponse): MessageDomainModel = MessageDomainModel(
        messageId = a.messageId,
        chatId = a.chatId,
        senderId = a.senderId,
        content = a.content,
        timestamp = a.timestamp
    )


    override fun convertBA(b: MessageDomainModel): MessageResponse = MessageResponse(
        messageId = b.messageId,
        chatId = b.chatId,
        senderId = b.senderId,
        content = b.content,
        timestamp = b.timestamp
    )

}

package ru.firevawe.firevawemobile.data.converters.db

import ru.firevawe.firevawemobile.data.database.entity.MessageEntity
import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel

internal class MessageDBConverter : BaseConverter<MessageEntity, MessageDomainModel> {
    override fun convertAB(a: MessageEntity): MessageDomainModel = MessageDomainModel(
        messageId = a.messageId,
        chatId = a.chatId,
        senderId = a.senderId,
        content = a.content,
        timestamp = a.timestamp
    )


    override fun convertBA(b: MessageDomainModel): MessageEntity = MessageEntity(
        messageId = b.messageId,
        chatId = b.chatId,
        senderId = b.senderId,
        content = b.content,
        timestamp = b.timestamp
    )

}

package ru.firevawe.firevawemobile.data.converters

import ru.firevawe.firevawemobile.data.database.entity.MessageEntity
import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.database.domain_model.MessageDTO

internal class MessageConverter : BaseConverter<MessageEntity, MessageDTO> {
    override fun convertAB(a: MessageEntity): MessageDTO {
        return MessageDTO(
            messageId = a.messageId,
            chatId = a.chatId,
            senderId = a.senderId,
            content = a.content,
            timestamp = a.timestamp
        )
    }

    override fun convertBA(b: MessageDTO): MessageEntity {
        return MessageEntity(
            messageId = b.messageId,
            chatId = b.chatId,
            senderId = b.senderId,
            content = b.content,
            timestamp = b.timestamp
        )
    }
}

package ru.firevawe.firevawemobile.presentation.components.converter

import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel
import ru.firevawe.firevawemobile.presentation.models.Message

internal class MessageConverter : BaseConverter<MessageDomainModel, Message> {
    override fun convertAB(a: MessageDomainModel): Message = Message(
        messageId = a.messageId,
        chatId = a.chatId,
        senderId = a.senderId,
        content = a.content,
        timestamp = a.timestamp
    )


    override fun convertBA(b: Message): MessageDomainModel = MessageDomainModel(
        messageId = b.messageId,
        chatId = b.chatId,
        senderId = b.senderId,
        content = b.content,
        timestamp = b.timestamp
    )

}

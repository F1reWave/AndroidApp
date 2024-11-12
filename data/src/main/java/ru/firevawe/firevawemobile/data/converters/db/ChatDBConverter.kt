package ru.firevawe.firevawemobile.data.converters.db

import ru.firevawe.firevawemobile.data.database.entity.ChatEntity
import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel

internal class ChatDBConverter : BaseConverter<ChatEntity, ChatDomainModel> {
    override fun convertAB(a: ChatEntity): ChatDomainModel = ChatDomainModel(
        chatId = a.chatId,
        isGroupChat = a.isGroupChat,
        chatName = a.chatName
    )


    override fun convertBA(b: ChatDomainModel): ChatEntity = ChatEntity(
        chatId = b.chatId,
        isGroupChat = b.isGroupChat,
        chatName = b.chatName
    )

}

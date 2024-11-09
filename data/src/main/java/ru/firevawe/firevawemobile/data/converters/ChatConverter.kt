package ru.firevawe.firevawemobile.data.converters

import ru.firevawe.firevawemobile.data.database.entity.ChatEntity
import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.database.domain_model.ChatDTO

internal class ChatConverter : BaseConverter<ChatEntity, ChatDTO> {
    override fun convertAB(a: ChatEntity): ChatDTO {
        return ChatDTO(
            chatId = a.chatId,
            isGroupChat = a.isGroupChat,
            chatName = a.chatName
        )
    }

    override fun convertBA(b: ChatDTO): ChatEntity {
        return ChatEntity(
            chatId = b.chatId,
            isGroupChat = b.isGroupChat,
            chatName = b.chatName
        )
    }
}

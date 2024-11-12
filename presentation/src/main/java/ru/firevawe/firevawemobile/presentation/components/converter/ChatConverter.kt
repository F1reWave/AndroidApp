package ru.firevawe.firevawemobile.presentation.components.converter

import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel
import ru.firevawe.firevawemobile.presentation.models.Chat

internal class ChatConverter : BaseConverter<ChatDomainModel, Chat> {

    override fun convertAB(a: ChatDomainModel): Chat = Chat(
        chatId = a.chatId,
        isGroupChat = a.isGroupChat,
        chatName = a.chatName
    )


    override fun convertBA(b: Chat): ChatDomainModel = ChatDomainModel(
        chatId = b.chatId,
        isGroupChat = b.isGroupChat,
        chatName = b.chatName
    )

}

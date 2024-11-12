package ru.firevawe.firevawemobile.data.converters.network

import ru.firevawe.firevawemobile.data.network.response_model.ChatResponse
import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel

internal class ChatNetworkConverter : BaseConverter<ChatResponse, ChatDomainModel> {

    override fun convertAB(a: ChatResponse): ChatDomainModel = ChatDomainModel(
        chatId = a.chatId,
        isGroupChat = a.isGroupChat,
        chatName = a.chatName
    )


    override fun convertBA(b: ChatDomainModel): ChatResponse = ChatResponse(
        chatId = b.chatId,
        isGroupChat = b.isGroupChat,
        chatName = b.chatName
    )

}

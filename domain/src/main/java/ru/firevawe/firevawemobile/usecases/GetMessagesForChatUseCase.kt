package ru.firevawe.firevawemobile.usecases

import ru.firevawe.firevawemobile.domain.database.repository.MessageDBRepository
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.MessageNetworkRepository

// UseCase для получения всех сообщений для чата
class GetMessagesForChatUseCase(
    private val messageDBRepository: MessageDBRepository,
    private val messageNetworkRepository: MessageNetworkRepository
) {
    suspend fun use(chatId: String): List<MessageDomainModel> {
        val messages = messageNetworkRepository.getMessagesForChat(chatId)
        messages.forEach { messageDBRepository.addMessage(it) }
        return messages
    }
}

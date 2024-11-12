package ru.firevawe.firevawemobile.usecases

import ru.firevawe.firevawemobile.domain.database.repository.ChatDBRepository
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.ChatNetworkRepository

class DeleteChatUseCase(
    private val chatDBRepository: ChatDBRepository,
    private val chatNetworkRepository: ChatNetworkRepository
) {
    suspend fun use(chat: ChatDomainModel) {
        chatNetworkRepository.deleteChat(chat)
        chatDBRepository.deleteChat(chat)
    }
}
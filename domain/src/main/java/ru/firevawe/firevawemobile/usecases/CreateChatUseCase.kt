package ru.firevawe.firevawemobile.usecases

import ru.firevawe.firevawemobile.domain.database.repository.ChatDBRepository
import ru.firevawe.firevawemobile.domain.database.repository.MessageDBRepository
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.ChatNetworkRepository
import ru.firevawe.firevawemobile.domain.network.repository.MessageNetworkRepository


// UseCase для создания чата
class CreateChatUseCase(
    private val chatDBRepository: ChatDBRepository,
    private val chatNetworkRepository: ChatNetworkRepository
) {
    suspend fun use(chat: ChatDomainModel) {
        chatNetworkRepository.createChat(chat)
        chatDBRepository.addChat(chat)
    }
}

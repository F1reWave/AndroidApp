package ru.firevawe.firevawemobile.usecases

import ru.firevawe.firevawemobile.domain.database.repository.ChatDBRepository
import ru.firevawe.firevawemobile.domain.database.repository.MessageDBRepository
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.ChatNetworkRepository
import ru.firevawe.firevawemobile.domain.network.repository.MessageNetworkRepository


// UseCase для получения чатов для пользователя
class GetAllChatsForUserUseCase(
    private val chatDBRepository: ChatDBRepository,
    private val chatNetworkRepository: ChatNetworkRepository
) {
    suspend fun use(username: String): List<ChatDomainModel> {
        val chats = chatNetworkRepository.getAllChatsForUser(username)
        chats.forEach { chatDBRepository.addChat(it) }
        return chats
    }
}

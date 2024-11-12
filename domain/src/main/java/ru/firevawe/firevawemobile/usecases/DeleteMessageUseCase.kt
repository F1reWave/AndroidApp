package ru.firevawe.firevawemobile.usecases

import ru.firevawe.firevawemobile.domain.database.repository.MessageDBRepository
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.MessageNetworkRepository


// UseCase для удаления сообщения
class DeleteMessageUseCase(
    private val messageDBRepository: MessageDBRepository,
    private val messageNetworkRepository: MessageNetworkRepository
) {
    suspend fun use(messageDomainModel: MessageDomainModel) {
        messageNetworkRepository.deleteMessage(messageDomainModel)
        messageDBRepository.deleteMessage(messageDomainModel)
    }
}
package ru.firevawe.firevawemobile.usecases

import ru.firevawe.firevawemobile.domain.database.repository.MessageDBRepository
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.MessageNetworkRepository

// UseCase для создания сообщения
class UpdateMessageUseCase(
    private val messageDBRepository: MessageDBRepository,
    private val messageNetworkRepository: MessageNetworkRepository
) {
    suspend fun use(messageDomainModel: MessageDomainModel) {
        // Создаем сообщение на сервере
        messageNetworkRepository.createMessage(messageDomainModel)
        // Сохраняем сообщение в БД
        messageDBRepository.addMessage(messageDomainModel)
    }
}
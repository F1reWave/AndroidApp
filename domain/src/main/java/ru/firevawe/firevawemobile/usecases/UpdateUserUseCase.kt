package ru.firevawe.firevawemobile.usecases

import ru.firevawe.firevawemobile.domain.database.repository.ChatDBRepository
import ru.firevawe.firevawemobile.domain.database.repository.MessageDBRepository
import ru.firevawe.firevawemobile.domain.database.repository.UserDBRepository
import ru.firevawe.firevawemobile.domain.domain_model.ChatDomainModel
import ru.firevawe.firevawemobile.domain.domain_model.MessageDomainModel
import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.ChatNetworkRepository
import ru.firevawe.firevawemobile.domain.network.repository.MessageNetworkRepository
import ru.firevawe.firevawemobile.domain.network.repository.UserNetworkRepository


// UseCase для обновления пользователя
class UpdateUserUseCase(
    private val userDBRepository: UserDBRepository,
    private val userNetworkRepository: UserNetworkRepository
) {
    suspend fun use(userDomainModel: UserDomainModel) {
        userNetworkRepository.updateUser(userDomainModel)
        userDBRepository.updateUser(userDomainModel)
    }
}


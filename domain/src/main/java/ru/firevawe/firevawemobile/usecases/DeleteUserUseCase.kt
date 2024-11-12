package ru.firevawe.firevawemobile.usecases

import ru.firevawe.firevawemobile.domain.database.repository.UserDBRepository
import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.UserNetworkRepository

class DeleteUserUseCase(
    private val userDBRepository: UserDBRepository,
    private val userNetworkRepository: UserNetworkRepository
) {
    suspend fun use(userDomainModel: UserDomainModel) {
        userNetworkRepository.createUser(userDomainModel)
        userDBRepository.addUser(userDomainModel)
    }
}
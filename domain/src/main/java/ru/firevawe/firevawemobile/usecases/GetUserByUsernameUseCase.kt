package ru.firevawe.firevawemobile.usecases

import ru.firevawe.firevawemobile.domain.database.repository.UserDBRepository
import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel
import ru.firevawe.firevawemobile.domain.network.repository.UserNetworkRepository

class GetUserByUsernameUseCase(
    private val userNetworkRepository: UserNetworkRepository
) {
    suspend fun use(username: String): UserDomainModel {
        return userNetworkRepository.getUserByUsername(username)
    }
}
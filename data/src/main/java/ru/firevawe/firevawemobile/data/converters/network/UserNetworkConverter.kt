package ru.firevawe.firevawemobile.data.converters.network

import ru.firevawe.firevawemobile.data.network.response_model.UserResponse
import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel

internal class UserNetworkConverter : BaseConverter<UserResponse, UserDomainModel> {
    override fun convertAB(a: UserResponse): UserDomainModel = UserDomainModel(
        username = a.username,
        name = a.name,
        avatarUrl = a.avatarUrl
    )


    override fun convertBA(b: UserDomainModel): UserResponse = UserResponse(
        username = b.username,
        name = b.name,
        avatarUrl = b.avatarUrl
    )

}

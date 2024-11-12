package ru.firevawe.firevawemobile.presentation.components.converter

import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel
import ru.firevawe.firevawemobile.presentation.models.User

internal class UserConverter : BaseConverter<UserDomainModel, User> {
    override fun convertAB(a: UserDomainModel): User = User(
        username = a.username,
        name = a.name,
        avatarUrl = a.avatarUrl
    )


    override fun convertBA(b: User): UserDomainModel = UserDomainModel(
        username = b.username,
        name = b.name,
        avatarUrl = b.avatarUrl
    )

}

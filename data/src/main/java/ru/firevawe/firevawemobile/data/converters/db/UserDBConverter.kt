package ru.firevawe.firevawemobile.data.converters.db

import ru.firevawe.firevawemobile.data.database.entity.UserEntity
import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.domain_model.UserDomainModel

internal class UserDBConverter : BaseConverter<UserEntity, UserDomainModel> {
    override fun convertAB(a: UserEntity): UserDomainModel = UserDomainModel(
        username = a.username,
        name = a.name,
        avatarUrl = a.avatarUrl
    )

    override fun convertBA(b: UserDomainModel): UserEntity = UserEntity(
        username = b.username,
        name = b.name,
        avatarUrl = b.avatarUrl
    )

}

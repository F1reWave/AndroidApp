package ru.firevawe.firevawemobile.data.converters

import ru.firevawe.firevawemobile.data.database.entity.UserEntity
import ru.firevawe.firevawemobile.domain.converter.BaseConverter
import ru.firevawe.firevawemobile.domain.database.domain_model.UserDTO

internal class UserConverter : BaseConverter<UserEntity, UserDTO> {
    override fun convertAB(a: UserEntity): UserDTO {
        return UserDTO(
            userId = a.userId,
            username = a.username,
            avatarUrl = a.avatarUrl
        )
    }

    override fun convertBA(b: UserDTO): UserEntity {
        return UserEntity(
            userId = b.userId,
            username = b.username,
            avatarUrl = b.avatarUrl
        )
    }
}

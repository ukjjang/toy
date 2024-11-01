package com.jinuk.toy.domain.user

import com.jinuk.toy.domain.user.value.Username
import com.jinuk.toy.infra.rdb.user.entity.UserEntity
import com.jinuk.toy.util.domainhelper.BaseDomain
import com.jinuk.toy.util.jbcrypt.Jbcrypt
import java.time.LocalDateTime

data class User(
    override val _id: Long? = null,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedAt: LocalDateTime = LocalDateTime.now(),
    val username: Username,
    val password: String,
) : BaseDomain(_id, createdAt, updatedAt) {
    companion object {
        fun signup(userCredentials: UserCredentials) =
            User(username = userCredentials.username, password = Jbcrypt.encrypt(userCredentials.password))
    }
}

internal fun UserEntity.toModel() =
    User(
        _id = id,
        username = Username(username),
        password = password,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

internal fun User.toEntity() =
    UserEntity(
        id = _id,
        username = username.value,
        password = password,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

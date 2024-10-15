package com.jinuk.toy.domain.user.service

import com.jinuk.toy.domain.user.jpa.UserRepository
import com.jinuk.toy.domain.user.value.Username
import org.springframework.stereotype.Service

@Service
class UserQueryService(
    private val userRepository: UserRepository,
) {

    fun findByUsername(username: Username) = userRepository.findByUsername(username)
}
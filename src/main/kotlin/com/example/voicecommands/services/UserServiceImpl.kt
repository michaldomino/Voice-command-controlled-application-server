package com.example.voicecommands.services

import com.example.voicecommands.dto.mapper.toUser
import com.example.voicecommands.dto.model.UserDTO
import com.example.voicecommands.model.User
import com.example.voicecommands.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,

    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) : UserService {
    override fun addUser(userDTO: UserDTO): User {
        val userToSave = userDTO.toUser()
        userToSave.password = bCryptPasswordEncoder.encode(userToSave.password)
        return userRepository.save(userToSave)
    }
}
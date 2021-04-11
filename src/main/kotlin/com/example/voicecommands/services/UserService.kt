package com.example.voicecommands.services

import com.example.voicecommands.dto.model.UserDTO
import com.example.voicecommands.model.User
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun addUser(userDTO: UserDTO): User
}
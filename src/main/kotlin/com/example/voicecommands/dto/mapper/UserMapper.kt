package com.example.voicecommands.dto.mapper

import com.example.voicecommands.dto.model.UserDTO
import com.example.voicecommands.model.User

fun UserDTO.toUser(): User {
    return User(username!!, password!!)
}
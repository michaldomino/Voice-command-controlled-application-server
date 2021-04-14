package com.example.voicecommands.services

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.dto.model.UserDTO
import com.example.voicecommands.model.User
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun saveUser(userDTO: UserDTO): UserDTO
    fun findAllUsers(): List<UserDTO>
    fun findUserById(id: String): UserDTO?
    fun findAllUsersByUsernameContains(username: String): List<UserDTO>
    fun findAllUsersBySharedNoteNameContains(sharedNoteName: String): List<UserDTO>
}
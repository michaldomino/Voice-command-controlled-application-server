package com.example.voicecommands.services

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.dto.model.UserDTO
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun saveUser(userDTO: UserDTO): UserDTO
    fun findAllUsers(): List<UserDTO>
    fun findUserById(id: String): UserDTO?
    fun findAllUsersByUsernameContains(username: String): List<UserDTO>
    fun findAllUsersByOwnedNotesCountBetween(minimumOwnedNotesCount: Int, maximumOwnedNotesCount: Int?): List<UserDTO>
    fun addSharedNoteToUser(id: String, noteDTOs: List<NoteDTO>): List<NoteDTO>
    fun updateUser(userDTO: UserDTO): UserDTO
    fun partialUpdateUser(userDTOToUpdate: UserDTO, userDTO: UserDTO): UserDTO
    fun deleteUserById(id: String)
}
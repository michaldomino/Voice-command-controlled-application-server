package com.example.voicecommands.services

import com.example.voicecommands.dto.mapper.toNoteDTO
import com.example.voicecommands.dto.mapper.toUser
import com.example.voicecommands.dto.mapper.toUserDTO
import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.dto.model.UserDTO
import com.example.voicecommands.repositories.NoteRepository
import com.example.voicecommands.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val noteRepository: NoteRepository,

    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) : UserService {
    override fun saveUser(userDTO: UserDTO): UserDTO {
        val userToSave = userDTO.toUser()
        userToSave.password = bCryptPasswordEncoder.encode(userToSave.password)
        return userRepository.save(userToSave).toUserDTO()
    }

    override fun findAllUsers(): List<UserDTO> {
        return userRepository.findAll().map { it.toUserDTO() }
    }

    override fun findUserById(id: String): UserDTO? {
        return userRepository.findByIdOrNull(id)?.toUserDTO()
    }

    override fun findAllUsersByUsernameContains(username: String): List<UserDTO> {
        return userRepository.findAllByUsernameContains(username).map { it.toUserDTO() }
    }

    override fun findAllUsersByOwnedNotesCountBetween(
        minimumOwnedNotesCount: Int,
        maximumOwnedNotesCount: Int?
    ): List<UserDTO> {
        var searchedUsers = userRepository.findAll().filter { it.ownedNotes.size >= minimumOwnedNotesCount }
        if (maximumOwnedNotesCount != null) {
            searchedUsers = searchedUsers.filter { it.ownedNotes.size <= maximumOwnedNotesCount }
        }
        return searchedUsers.map { it.toUserDTO() }
    }

    override fun addSharedNoteToUser(id: String, noteDTOs: List<NoteDTO>): List<NoteDTO> {
        val user = userRepository.findById(id).get()
        val notesToAdd = noteRepository.findAllById(noteDTOs.map { it.id })
        user.sharedNotes.addAll(notesToAdd)
        userRepository.save(user)
        return notesToAdd.map { it.toNoteDTO() }
    }

    override fun updateUser(userDTO: UserDTO): UserDTO {
        val userToSave = userRepository.findById(userDTO.id!!).get()
        userToSave.username = userDTO.username!!
        userToSave.password = bCryptPasswordEncoder.encode(userDTO.password)
        return userRepository.save(userToSave).toUserDTO()
    }

    override fun partialUpdateUser(userDTOToUpdate: UserDTO, userDTO: UserDTO): UserDTO {
        val userToUpdate = userRepository.findById(userDTOToUpdate.id!!).get()
        if (userDTO.username != null) {
            userToUpdate.username = userDTO.username
        }
        if (userDTO.password != null) {
            val passwordHash = bCryptPasswordEncoder.encode(userDTO.password)
            userToUpdate.password = passwordHash
        }
        return userRepository.save(userToUpdate).toUserDTO()
    }

    override fun deleteUserById(id: String) {
        userRepository.deleteById(id)
    }
}
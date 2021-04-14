package com.example.voicecommands.services

import com.example.voicecommands.dto.mapper.toNote
import com.example.voicecommands.dto.mapper.toUser
import com.example.voicecommands.dto.mapper.toUserDTO
import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.dto.model.UserDTO
import com.example.voicecommands.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,

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

    override fun findAllUsersBySharedNoteNameContains(sharedNoteName: String): List<UserDTO> {
        userRepository.findAll()
    }
}
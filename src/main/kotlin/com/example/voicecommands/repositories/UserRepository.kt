package com.example.voicecommands.repositories

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.model.Note
import com.example.voicecommands.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {
    fun findAllByUsernameContains(username: String): List<User>
    fun findAllBySharedNotesContains(note: Note): List<User>
    fun findAllBySharedNotes
}
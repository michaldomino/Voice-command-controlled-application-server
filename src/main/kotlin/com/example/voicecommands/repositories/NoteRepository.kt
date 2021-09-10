package com.example.voicecommands.repositories

import com.example.voicecommands.enums.NoteType
import com.example.voicecommands.model.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository : CrudRepository<Note, String> {
    fun findAllByType(type: NoteType): List<Note>
    fun findAllByNameContains(name: String): List<Note>
}
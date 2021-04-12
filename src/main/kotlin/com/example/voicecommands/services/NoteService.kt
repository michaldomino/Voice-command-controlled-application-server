package com.example.voicecommands.services

import com.example.voicecommands.dto.model.NoteDTO
import org.springframework.stereotype.Service

@Service
interface NoteService {
    fun findAllNotes(): List<NoteDTO>
    fun findNoteById(id: String): NoteDTO?
    fun deleteNoteById(id: String)
    fun saveNote(noteDTO: NoteDTO): String?
}
package com.example.voicecommands.services

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.enums.NoteType
import org.springframework.stereotype.Service

@Service
interface NoteService {
    fun findAllNotes(): List<NoteDTO>
    fun findNoteById(id: String): NoteDTO?
    fun deleteNoteById(id: String)
    fun saveNote(noteDTO: NoteDTO): NoteDTO?
    fun findAllNotesByType(type: NoteType): List<NoteDTO>
    fun findAllNotesByNameContains(name: String): List<NoteDTO>
    fun updateNote(noteDTO: NoteDTO): NoteDTO
    fun partialUpdateNote(noteDTOToUpdate: NoteDTO, noteDTO: NoteDTO): NoteDTO
}
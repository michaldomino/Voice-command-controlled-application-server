package com.example.voicecommands.dto.mapper

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.model.Note
import com.example.voicecommands.repositories.UserRepository

fun NoteDTO.toNote(userRepository: UserRepository): Note {
    return Note(name!!, type!!, userRepository.findById(ownerId!!).get())
}

fun Note.toNoteDTO() = NoteDTO(id, name, type, owner.id)
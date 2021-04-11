package com.example.voicecommands.services

import org.springframework.stereotype.Service

@Service
interface NoteService {
    fun deleteNoteById(id: String)
}
package com.example.voicecommands.repositories

import com.example.voicecommands.model.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository: CrudRepository<Note, Long>
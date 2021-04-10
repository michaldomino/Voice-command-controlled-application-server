package com.example.voicecommands.repositories

import com.example.voicecommands.model.TextNote
import org.springframework.data.repository.CrudRepository

interface TextNoteRepository : CrudRepository<TextNote, Long>
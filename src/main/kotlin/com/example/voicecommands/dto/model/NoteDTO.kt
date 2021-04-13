package com.example.voicecommands.dto.model

import com.example.voicecommands.enums.NoteType

data class NoteDTO(
    val id: String? = null,
    val name: String? = null,
    val type: NoteType? = null,
    val ownerId: String? = null
)
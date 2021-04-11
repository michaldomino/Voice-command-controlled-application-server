package com.example.voicecommands.dto.model

import com.example.voicecommands.enums.NoteType

data class NoteDTO(
    var id: String? = null,
    var name: String? = null,
    var type: NoteType? = null,
    var ownerId: String? = null
)
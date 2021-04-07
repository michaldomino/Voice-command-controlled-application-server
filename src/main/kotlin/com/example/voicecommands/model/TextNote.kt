package com.example.voicecommands.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class TextNote(
        @Id
        @OneToOne
        var note: Note,
        var text: String
)

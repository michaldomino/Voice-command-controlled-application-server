package com.example.voicecommands.model

import javax.persistence.*

@Entity
data class TextNote(
    @Id
    var id: Long,

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    var note: Note,

    var text: String
)

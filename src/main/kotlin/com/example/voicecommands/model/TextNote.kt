package com.example.voicecommands.model

import javax.persistence.*

@Entity
data class TextNote(
    @Id
    var id: String,

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    var note: Note,

    var text: String
)

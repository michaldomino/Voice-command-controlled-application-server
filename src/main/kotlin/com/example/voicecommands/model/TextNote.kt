package com.example.voicecommands.model

import javax.persistence.*

@Entity
class TextNote(
    @OneToOne
    @PrimaryKeyJoinColumn
    var note: Note,

    var text: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}

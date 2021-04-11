package com.example.voicecommands.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
class TextNote(
    @OneToOne
    var note: Note,

    var text: String,
) {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: String? = null
}

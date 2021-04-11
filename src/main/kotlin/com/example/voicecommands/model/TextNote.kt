package com.example.voicecommands.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.MapsId
import javax.persistence.OneToOne

@Entity
class TextNote(
    @OneToOne
    @MapsId
    var note: Note,

    var text: String,
) {
    @Id
    var id: String? = null
}

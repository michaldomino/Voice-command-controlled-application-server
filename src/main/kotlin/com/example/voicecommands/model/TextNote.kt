package com.example.voicecommands.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.MapsId
import javax.persistence.OneToOne

@Entity
class TextNote(
    @OneToOne(orphanRemoval = true)
    @MapsId
    var note: Note,
) {
    @Id
    var id: String? = null

    var text: String = ""
}

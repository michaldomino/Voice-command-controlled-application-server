package com.example.voicecommands.model

import com.example.voicecommands.enums.NoteType
import javax.persistence.*

@Entity
data class Note(
    var name: String,

    @Enumerated(EnumType.STRING)
    var noteType: NoteType,

    @ManyToOne
    var owner: User,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToMany(mappedBy = "sharedNotes")
    var sharedWith: MutableSet<User> = mutableSetOf()
}

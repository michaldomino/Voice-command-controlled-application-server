package com.example.voicecommands.model

import com.example.voicecommands.enums.NoteType
import javax.persistence.*

@Entity
data class Note(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var name: String,

    @Enumerated(EnumType.STRING)
    var noteType: NoteType,

    @ManyToOne
    var owner: User,

    @ManyToMany(mappedBy = "sharedNotes")
    var sharedWith: MutableSet<User> = mutableSetOf()
)

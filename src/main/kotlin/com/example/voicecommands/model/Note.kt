package com.example.voicecommands.model

import com.example.voicecommands.enums.NoteType
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Note(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        var id: String,

        var name: String,

        @Enumerated(EnumType.STRING)
        var noteType: NoteType,

        @ManyToOne
        var owner: User,

        @ManyToMany(mappedBy = "sharedNotes")
        var sharedWith: MutableSet<User> = mutableSetOf()
)

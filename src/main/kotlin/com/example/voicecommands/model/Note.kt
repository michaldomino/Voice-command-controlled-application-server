package com.example.voicecommands.model

import com.example.voicecommands.enums.NoteType
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Note(
    var name: String,

    @Enumerated(EnumType.STRING)
    var type: NoteType,

    @ManyToOne
    var owner: User,
) {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: String? = null

    @ManyToMany(mappedBy = "sharedNotes")
    var sharedWith: MutableSet<User> = mutableSetOf()
}

package com.example.voicecommands.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Note(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: String,

    var name: String,

    @ManyToOne
    var owner: User,

    @ManyToMany
    var sharedWith: MutableSet<User> = mutableSetOf()
)

package com.example.voicecommands.model

import javax.persistence.*

@Entity
data class TaskList(
    @OneToOne(orphanRemoval = true)
    @MapsId
    var note: Note,
) {
    @Id
    var id: String? = null

    @OneToMany
    var tasks: MutableSet<Task> = mutableSetOf()
}

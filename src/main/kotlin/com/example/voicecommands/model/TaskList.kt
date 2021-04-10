package com.example.voicecommands.model

import javax.persistence.*

@Entity
data class TaskList(
    @OneToOne
    var note: Note,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @OneToMany
    var tasks: MutableSet<Task> = mutableSetOf()
}

package com.example.voicecommands.model

import javax.persistence.*

@Entity
data class TaskList(
    @OneToOne
    @PrimaryKeyJoinColumn
    var note: Note,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @OneToMany
    var tasks: MutableSet<Task> = mutableSetOf()
}

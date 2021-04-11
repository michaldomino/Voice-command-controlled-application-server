package com.example.voicecommands.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class TaskList(
    @OneToOne
    var note: Note,
) {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    var id: String? = null

    @OneToMany
    var tasks: MutableSet<Task> = mutableSetOf()
}

package com.example.voicecommands.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
data class TaskList(
        @Id
        @OneToOne
        var note: Note,
        @OneToMany
        var tasks: MutableSet<Task>,
)

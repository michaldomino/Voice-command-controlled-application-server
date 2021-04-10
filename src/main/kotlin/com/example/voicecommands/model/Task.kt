package com.example.voicecommands.model

import javax.persistence.*

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @ManyToOne
    var note: Note,

    var taskName: String,

    var isChecked: Boolean,
)

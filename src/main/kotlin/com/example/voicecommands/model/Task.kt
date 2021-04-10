package com.example.voicecommands.model

import javax.persistence.*

@Entity
data class Task(
    @ManyToOne
    var taskList: TaskList,

    var taskName: String,

    var isChecked: Boolean = false,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}

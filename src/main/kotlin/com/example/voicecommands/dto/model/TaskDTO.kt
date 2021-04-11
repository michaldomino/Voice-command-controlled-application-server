package com.example.voicecommands.dto.model

data class TaskDTO(
    val id: String? = null,
    var taskListId: String? = null,
    var name: String? = null,
    var isChecked: Boolean = false,
)

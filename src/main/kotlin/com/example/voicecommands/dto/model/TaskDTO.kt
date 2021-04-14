package com.example.voicecommands.dto.model

data class TaskDTO(
    val id: String? = null,
    val taskListId: String? = null,
    val name: String? = null,
    val isChecked: Boolean = false,
)

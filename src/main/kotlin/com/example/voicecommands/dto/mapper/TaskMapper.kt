package com.example.voicecommands.dto.mapper

import com.example.voicecommands.dto.model.TaskDTO
import com.example.voicecommands.model.Task
import com.example.voicecommands.repositories.TaskListRepository

fun TaskDTO.toTask(taskListRepository: TaskListRepository): Task {
    return Task(taskListRepository.findById(taskListId!!).get(), name!!, isChecked)
}
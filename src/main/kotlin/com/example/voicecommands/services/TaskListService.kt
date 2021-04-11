package com.example.voicecommands.services

import com.example.voicecommands.dto.model.TaskListDTO
import org.springframework.stereotype.Service

@Service
interface TaskListService {

    fun deleteTaskList(taskListDTO: TaskListDTO)
}
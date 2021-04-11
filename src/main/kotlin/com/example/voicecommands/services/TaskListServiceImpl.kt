package com.example.voicecommands.services

import com.example.voicecommands.dto.model.TaskListDTO
import com.example.voicecommands.repositories.TaskListRepository
import com.example.voicecommands.repositories.TaskRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TaskListServiceImpl(
    private val taskListRepository: TaskListRepository,
    private val taskRepository: TaskRepository
) : TaskListService {
    override fun deleteTaskList(taskListDTO: TaskListDTO) {
        if (taskListDTO.id != null) {
            val taskListToDelete = taskListRepository.findByIdOrNull(taskListDTO.id)
            if (taskListToDelete != null) {
                taskRepository.deleteAll(taskListToDelete.tasks)
                taskListRepository.delete(taskListToDelete)
            }
        }
    }
}
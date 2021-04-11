package com.example.voicecommands.repositories

import com.example.voicecommands.model.Task
import com.example.voicecommands.model.TaskList
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, String> {
    fun findAllByTaskList(taskList: TaskList): Set<Task>
}
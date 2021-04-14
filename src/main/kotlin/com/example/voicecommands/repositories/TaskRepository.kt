package com.example.voicecommands.repositories

import com.example.voicecommands.model.Task
import com.example.voicecommands.model.TaskList
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "tasks", path = "taskss")
interface TaskRepository : CrudRepository<Task, String> {
    fun findAllByTaskList(taskList: TaskList): Set<Task>
}
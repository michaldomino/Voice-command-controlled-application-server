package com.example.voicecommands.repositories

import com.example.voicecommands.model.TaskList
import org.springframework.data.repository.CrudRepository

interface TaskListRepository: CrudRepository<TaskList, String>
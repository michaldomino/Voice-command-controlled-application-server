package com.example.voicecommands.repositories

import com.example.voicecommands.model.Task
import org.springframework.data.repository.CrudRepository

interface TaskRepository: CrudRepository<Task, Long>
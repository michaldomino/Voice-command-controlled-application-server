package com.example.voicecommands.controllers

import com.example.voicecommands.repositories.NoteRepository
import com.example.voicecommands.repositories.TaskRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class TaskController(
    private val taskRepository: TaskRepository
) {

    @RequestMapping("/tasks")
    fun getNotes(model: Model): String {
        model.addAttribute("tasks", taskRepository.findAll())
        return "task/list"
    }
}
package com.example.voicecommands.controllers

import com.example.voicecommands.repositories.NoteRepository
import com.example.voicecommands.repositories.TaskListRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class TaskListController(
    private val taskListRepository: TaskListRepository
) {

    @RequestMapping("/task_lists")
    fun getNotes(model: Model): String {
        model.addAttribute("taskLists", taskListRepository.findAll())
        return "task_list/list"
    }
}
package com.example.voicecommands.controllers

import com.example.voicecommands.repositories.TaskListRepository
import com.example.voicecommands.repositories.TaskRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class TaskListController(
    private val taskListRepository: TaskListRepository,
    private val taskRepository: TaskRepository,
) {

    @RequestMapping("/task-lists", "/task-list/list")
    fun getNotes(model: Model): String {
        model.addAttribute("taskLists", taskListRepository.findAll())
        return "task-list/list"
    }

    @RequestMapping("/task-list/{id}/tasks")
    fun getTaskListTasks(model: Model, @PathVariable("id") id: String): String {
        val taskList = taskListRepository.findByIdOrNull(id)
        if (taskList != null) {
            model.addAttribute("tasks", taskRepository.findAllByTaskList(taskList))
            model.addAttribute(
                "filter",
                "Task list: ${taskList.note.name}"
            )
        } else {
            model.addAttribute("tasks", ArrayList<Any>())
            model.addAttribute("filter", "Task list for this id does not exist")
        }
        return "task/list"
    }
}
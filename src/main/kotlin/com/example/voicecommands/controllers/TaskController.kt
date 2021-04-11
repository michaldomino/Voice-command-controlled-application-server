package com.example.voicecommands.controllers

import com.example.voicecommands.dto.mapper.toTask
import com.example.voicecommands.dto.model.TaskDTO
import com.example.voicecommands.repositories.TaskListRepository
import com.example.voicecommands.repositories.TaskRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class TaskController(
    private val taskRepository: TaskRepository,
    private val taskListRepository: TaskListRepository,
) {

    @RequestMapping("/tasks")
    fun getNotes(model: Model): String {
        model.addAttribute("tasks", taskRepository.findAll())
        return "task/list"
    }

    @RequestMapping("/task/{id}/show")
    fun getTaskListDetails(model: Model, @PathVariable("id") id: String): String {
        model.addAttribute("task", taskRepository.findById(id).get())
        return "task/show"
    }

    @RequestMapping("/task/{id}/delete")
    fun deleteTaskList(@PathVariable("id") id: String): String {
        taskRepository.deleteById(id)
        return "redirect:/tasks"
    }

    @GetMapping
    @RequestMapping("/task/new")
    fun newTaskList(model: Model): String {
        model.addAttribute("task", TaskDTO())
        model.addAttribute("taskLists", taskListRepository.findAll())
        return "task/addedit"
    }

    @PostMapping("task")
    fun saveOrUpdate(@ModelAttribute taskDTO: TaskDTO): String {
        if (taskDTO.id == null) {
            return "redirect:/tasks"
        }
        val taskToSave = taskDTO.toTask(taskListRepository)
        taskRepository.save(taskToSave)
        return "redirect:/tasks"
    }
}
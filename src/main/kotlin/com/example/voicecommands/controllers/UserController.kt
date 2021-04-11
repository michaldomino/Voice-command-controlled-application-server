package com.example.voicecommands.controllers

import com.example.voicecommands.dto.model.UserDTO
import com.example.voicecommands.repositories.UserRepository
import com.example.voicecommands.services.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class UserController(
    private val userRepository: UserRepository,

    private val userService: UserService,
) {

    @RequestMapping("/users")
    fun getNotes(model: Model): String {
        model.addAttribute("users", userRepository.findAll())
        return "user/list"
    }

    @RequestMapping("/user/{id}/show")
    fun getTaskListDetails(model: Model, @PathVariable("id") id: String): String {
        model.addAttribute("user", userRepository.findById(id).get())
        return "user/show"
    }

    @RequestMapping("/user/{id}/delete")
    fun deleteTaskList(@PathVariable("id") id: String): String {
        userRepository.deleteById(id)
        return "redirect:/users"
    }

    @GetMapping
    @RequestMapping("/user/new")
    fun newTaskList(model: Model): String {
        model.addAttribute("user", UserDTO())
        return "user/addedit"
    }

    @PostMapping("user")
    fun saveOrUpdate(@ModelAttribute userDTO: UserDTO): String {
        if (userDTO.id == null) {
            return "redirect:/users"
        }
        val user = userRepository.findByIdOrNull(userDTO.id)
        if (user == null) {
            userService.addUser(userDTO)
        }
        return "redirect:/users"
    }
}
package com.example.voicecommands.controllers

import com.example.voicecommands.repositories.NoteRepository
import com.example.voicecommands.repositories.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class UserController(
    private val userRepository: UserRepository
) {

    @RequestMapping("/users")
    fun getNotes(model: Model): String {
        model.addAttribute("users", userRepository.findAll())
        return "user/list"
    }
}
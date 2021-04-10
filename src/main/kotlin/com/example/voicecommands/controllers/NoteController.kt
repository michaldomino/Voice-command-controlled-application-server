package com.example.voicecommands.controllers

import com.example.voicecommands.repositories.NoteRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class NoteController(
    private val noteRepository: NoteRepository
) {

    @RequestMapping("/notes")
    fun getNotes(model: Model): String {
        model.addAttribute("notes", noteRepository.findAll())
        return "note/list"
    }
}
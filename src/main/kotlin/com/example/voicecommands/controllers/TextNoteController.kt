package com.example.voicecommands.controllers

import com.example.voicecommands.repositories.NoteRepository
import com.example.voicecommands.repositories.TextNoteRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class TextNoteController(
    private val textNoteRepository: TextNoteRepository
) {

    @RequestMapping("/text_notes")
    fun getNotes(model: Model): String {
        model.addAttribute("textNotes", textNoteRepository.findAll())
        return "text_note/list"
    }
}
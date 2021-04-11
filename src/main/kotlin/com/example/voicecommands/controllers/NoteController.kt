package com.example.voicecommands.controllers

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.enums.NoteType
import com.example.voicecommands.repositories.*
import com.example.voicecommands.services.NoteService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class NoteController(
    private val noteRepository: NoteRepository,
    private val userRepository: UserRepository,
    private val noteService: NoteService,
) {

    @RequestMapping("/notes", "/note/list")
    fun getNotes(model: Model): String {
        model.addAttribute("notes", noteRepository.findAll())
        return "note/list"
    }

    @RequestMapping("/note/{id}/show")
    fun getTaskListDetails(model: Model, @PathVariable("id") id: String): String {
        model.addAttribute("note", noteRepository.findById(id).get())
        return "note/show"
    }

    @RequestMapping("/note/{id}/delete")
    fun deleteTaskList(@PathVariable("id") id: String): String {
        noteService.deleteNoteById(id)
        return "redirect:/notes"
    }

    @GetMapping
    @RequestMapping("/note/new")
    fun newTaskList(model: Model): String {
        model.addAttribute("note", NoteDTO())
        model.addAttribute("types", arrayListOf(NoteType.TASK_LIST, NoteType.TEXT_NOTE))
        model.addAttribute("users", userRepository.findAll())
        return "note/addedit"
    }

    @PostMapping("note")
    fun saveOrUpdate(@ModelAttribute noteDTO: NoteDTO): String {
        if (noteDTO.id == null) {
            return "redirect:/notes"
        }
        noteService.saveNote(noteDTO)
        return "redirect:/notes"
    }
}

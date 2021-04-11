package com.example.voicecommands.controllers

import com.example.voicecommands.dto.mapper.toNote
import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.enums.NoteType
import com.example.voicecommands.model.TaskList
import com.example.voicecommands.model.TextNote
import com.example.voicecommands.repositories.NoteRepository
import com.example.voicecommands.repositories.TaskListRepository
import com.example.voicecommands.repositories.TextNoteRepository
import com.example.voicecommands.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class NoteController(
    private val noteRepository: NoteRepository,
    private val taskListRepository: TaskListRepository,
    private val textNoteRepository: TextNoteRepository,
    private val userRepository: UserRepository,
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
        val note = noteRepository.findByIdOrNull(id)
        when (note?.type) {
            NoteType.TASK_LIST -> taskListRepository.deleteById(id)
            NoteType.TEXT_NOTE -> textNoteRepository.deleteById(id)
        }
        return "redirect:/notes"
    }
}

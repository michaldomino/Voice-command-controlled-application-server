package com.example.voicecommands.controllers.rest

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.services.NoteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/note")
class NoteRestController(
    private val noteService: NoteService,
) {
    @GetMapping("list")
    fun getNotes(): List<NoteDTO> {
        return noteService.findAllNotes()
    }

    @GetMapping("{id}")
    fun getNoteById(@PathVariable id: String): NoteDTO? {
        return noteService.findNoteById(id)
    }
}
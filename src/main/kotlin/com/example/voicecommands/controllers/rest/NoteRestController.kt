package com.example.voicecommands.controllers.rest

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.enums.NoteType
import com.example.voicecommands.services.NoteService
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/search/findAllByType")
    fun getNotesByType(@RequestParam type: NoteType): List<NoteDTO> {
        return noteService.findAllNotesByType(type)
    }

    @GetMapping("/search/findAllByNameLike")
    fun getNotesByNameLike(@RequestParam name: String): List<NoteDTO> {
        return noteService.findAllNotesByNameContains(name)
    }
}
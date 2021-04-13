package com.example.voicecommands.controllers.rest

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.enums.NoteType
import com.example.voicecommands.services.NoteService
import org.springframework.http.ResponseEntity
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
    fun getNoteById(@PathVariable id: String): ResponseEntity<NoteDTO> {
        val note = noteService.findNoteById(id)
        if (note == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.accepted().body(note)
    }

    @GetMapping("/search/findAllByType")
    fun getNotesByType(@RequestParam type: NoteType): ResponseEntity<List<NoteDTO>> {
        return ResponseEntity.accepted().body(noteService.findAllNotesByType(type))
    }

    @GetMapping("/search/findAllByNameLike")
    fun getNotesByNameLike(@RequestParam name: String): ResponseEntity<List<NoteDTO>> {
        return ResponseEntity.accepted().body(noteService.findAllNotesByNameContains(name))
    }

    @DeleteMapping("{id}")
    fun deleteNote(@PathVariable id: String): ResponseEntity<Any> {
        if (noteService.findNoteById(id) == null) {
            return ResponseEntity.notFound().build()
        }
        noteService.deleteNoteById(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("{id}")
    fun updateNote(@PathVariable id: String, @RequestBody noteDTO: NoteDTO): ResponseEntity<NoteDTO> {
        val note = noteService.findNoteById(id)
        if (note == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.accepted().body(noteService.updateNote(noteDTO))
    }
}
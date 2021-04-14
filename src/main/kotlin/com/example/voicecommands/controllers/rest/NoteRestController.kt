package com.example.voicecommands.controllers.rest

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.enums.NoteType
import com.example.voicecommands.services.NoteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/note")
class NoteRestController(
    private val noteService: NoteService,
) {
    @PostMapping
    fun createNote(@RequestBody noteDTO: NoteDTO): ResponseEntity<NoteDTO> {
        val badResponse: ResponseEntity<NoteDTO> = ResponseEntity.badRequest().build()
        if (noteDTO.id == null) {
            return badResponse
        }
        val note = noteService.findNoteById(noteDTO.id)
        if (note != null) {
            return badResponse
        }
        return ResponseEntity<NoteDTO>(noteService.saveNote(noteDTO), HttpStatus.CREATED)
    }

    @GetMapping("list")
    fun getNotes(): ResponseEntity<List<NoteDTO>> {
        return ResponseEntity.ok(noteService.findAllNotes())
    }

    @GetMapping("{id}")
    fun getNoteById(@PathVariable id: String): ResponseEntity<NoteDTO> {
        val note = noteService.findNoteById(id)
        if (note == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(note)
    }

    @GetMapping("/search/findAllByType")
    fun getNotesByType(@RequestParam type: NoteType): ResponseEntity<List<NoteDTO>> {
        return ResponseEntity.ok(noteService.findAllNotesByType(type))
    }

    @GetMapping("/search/findAllByNameContains")
    fun getNotesByNameContains(@RequestParam name: String): ResponseEntity<List<NoteDTO>> {
        return ResponseEntity.ok(noteService.findAllNotesByNameContains(name))
    }

    @PutMapping("{id}")
    fun updateNote(@PathVariable id: String, @RequestBody noteDTO: NoteDTO): ResponseEntity<NoteDTO> {
        val note = noteService.findNoteById(id)
        if (note == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(noteService.updateNote(noteDTO))
    }

    @PatchMapping("{id}")
    fun partialUpdateNote(@PathVariable id: String, @RequestBody noteDTO: NoteDTO): ResponseEntity<NoteDTO> {
        val note = noteService.findNoteById(id)
        if (note == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(noteService.partialUpdateNote(note, noteDTO))
    }

    @DeleteMapping("{id}")
    fun deleteNote(@PathVariable id: String): ResponseEntity<Any> {
        if (noteService.findNoteById(id) == null) {
            return ResponseEntity.notFound().build()
        }
        noteService.deleteNoteById(id)
        return ResponseEntity.noContent().build()
    }
}

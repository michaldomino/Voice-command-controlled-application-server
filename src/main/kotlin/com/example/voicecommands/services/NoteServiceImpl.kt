package com.example.voicecommands.services

import com.example.voicecommands.dto.mapper.toNote
import com.example.voicecommands.dto.mapper.toNoteDTO
import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.dto.model.TaskListDTO
import com.example.voicecommands.enums.NoteType
import com.example.voicecommands.model.TaskList
import com.example.voicecommands.model.TextNote
import com.example.voicecommands.repositories.NoteRepository
import com.example.voicecommands.repositories.TaskListRepository
import com.example.voicecommands.repositories.TextNoteRepository
import com.example.voicecommands.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NoteServiceImpl(
    private val noteRepository: NoteRepository,
    private val taskListRepository: TaskListRepository,
    private val textNoteRepository: TextNoteRepository,
    private val userRepository: UserRepository,

    private val taskListService: TaskListService,
) : NoteService {
    override fun findAllNotes(): List<NoteDTO> {
        return noteRepository.findAll().map { it.toNoteDTO() }
    }

    override fun findNoteById(id: String): NoteDTO? {
        return noteRepository.findByIdOrNull(id)?.toNoteDTO()
    }

    override fun deleteNoteById(id: String) {
        val note = noteRepository.findByIdOrNull(id)
        when (note?.type) {
            NoteType.TASK_LIST -> taskListService.deleteTaskList(TaskListDTO(id))
            NoteType.TEXT_NOTE -> textNoteRepository.deleteById(id)
        }
    }

    override fun saveNote(noteDTO: NoteDTO): NoteDTO {
        val detachedNote = noteDTO.toNote(userRepository)
        return when (detachedNote.type) {
            NoteType.TASK_LIST -> {
                val taskListToSave = TaskList(detachedNote)
                taskListRepository.save(taskListToSave)
                NoteDTO(taskListToSave.id, noteDTO.name, noteDTO.type, noteDTO.ownerId)
            }
            NoteType.TEXT_NOTE -> {
                val textNoteToSave = TextNote(detachedNote)
                textNoteRepository.save(textNoteToSave)
                NoteDTO(textNoteToSave.id, noteDTO.name, noteDTO.type, noteDTO.ownerId)
            }
        }
    }

    override fun findAllNotesByType(type: NoteType): List<NoteDTO> {
        return noteRepository.findAllByType(type).map { it.toNoteDTO() }
    }

    override fun findAllNotesByNameContains(name: String): List<NoteDTO> {
        return noteRepository.findAllByNameContains(name).map { it.toNoteDTO() }
    }

    override fun updateNote(noteDTO: NoteDTO): NoteDTO {
        val noteToUpdate = noteRepository.findById(noteDTO.id!!).get()
        noteToUpdate.name = noteDTO.name!!
        noteToUpdate.type = noteDTO.type!!
        return noteRepository.save(noteToUpdate).toNoteDTO()
    }

    override fun partialUpdateNote(noteDTOToUpdate: NoteDTO, noteDTO: NoteDTO): NoteDTO {
        val noteToUpdate = noteRepository.findById(noteDTOToUpdate.id!!).get()
        if (noteDTO.name != null) {
            noteToUpdate.name = noteDTO.name
        }
        if (noteDTO.type != null) {
            noteToUpdate.type = noteDTO.type
        }
        if (noteDTO.ownerId != null) {
            val owner = userRepository.findByIdOrNull(noteDTO.ownerId)
            if (owner != null) {
                noteToUpdate.owner = owner
            }
        }
        return noteRepository.save(noteToUpdate).toNoteDTO()
    }
}
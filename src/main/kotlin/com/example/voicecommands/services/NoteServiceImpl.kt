package com.example.voicecommands.services

import com.example.voicecommands.dto.mapper.toNote
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
    override fun deleteNoteById(id: String) {
        val note = noteRepository.findByIdOrNull(id)
        when (note?.type) {
            NoteType.TASK_LIST -> taskListService.deleteTaskList(TaskListDTO(id))
            NoteType.TEXT_NOTE -> textNoteRepository.deleteById(id)
        }
    }

    override fun saveNote(noteDTO: NoteDTO): String? {
        val note = noteRepository.findByIdOrNull(noteDTO.id!!)
        if (note == null) {
            val detachedNote = noteDTO.toNote(userRepository)
            return when (detachedNote.type) {
                NoteType.TASK_LIST -> {
                    val taskListToSave = TaskList(detachedNote)
                    taskListRepository.save(taskListToSave)
                    taskListToSave.id
                }
                NoteType.TEXT_NOTE -> {
                    val textNoteToSave= TextNote(detachedNote)
                    textNoteRepository.save(textNoteToSave)
                    textNoteToSave.id
                }
            }
        }
        return null


//        if (note == null) {
//            val detachedNote = noteDTO.toNote(userRepository)
//            return when (detachedNote.type) {
//                    val savedTaskList = taskListRepository.save(TaskList(detachedNote))
//                    "redirect:/note/${savedTaskList.id}/show"
//                }
//                NoteType.TEXT_NOTE -> {
//                    val savedTextNote = textNoteRepository.save(TextNote(detachedNote))
//                    "redirect:/note/${savedTextNote.id}/show"
//                }
//            }
    }
}
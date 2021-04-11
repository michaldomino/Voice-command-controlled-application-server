package com.example.voicecommands.services

import com.example.voicecommands.dto.model.TaskListDTO
import com.example.voicecommands.enums.NoteType
import com.example.voicecommands.repositories.NoteRepository
import com.example.voicecommands.repositories.TaskListRepository
import com.example.voicecommands.repositories.TextNoteRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NoteServiceImpl(
    private val noteRepository: NoteRepository,
    private val textNoteRepository: TextNoteRepository,

    private val taskListService: TaskListService,
) : NoteService {
    override fun deleteNoteById(id: String) {
        val note = noteRepository.findByIdOrNull(id)
        when (note?.type) {
            NoteType.TASK_LIST -> {
                taskListService.deleteTaskList(TaskListDTO(id))
//                val taskListToDelete = taskListRepository.findByIdOrNull(id)
//                taskListToDelete?.let {
//                    taskRepository.deleteAll(it.tasks)
//                    taskListRepository.deleteById(id)
//                }
            }
            NoteType.TEXT_NOTE -> textNoteRepository.deleteById(id)
        }
    }
}
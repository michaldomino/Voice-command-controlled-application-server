package com.example.voicecommands.tools

import com.example.voicecommands.enums.NoteType
import com.example.voicecommands.model.*
import com.example.voicecommands.repositories.*
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class DBInflater(
    private val noteRepository: NoteRepository,
    private val taskRepository: TaskRepository,
    private val taskListRepository: TaskListRepository,
    private val textNoteRepository: TextNoteRepository,
    private val userRepository: UserRepository,

    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(p0: ContextRefreshedEvent) {
        initData()
    }

    private fun initData() {
        val user = User("admin", bCryptPasswordEncoder.encode("admin"))
        userRepository.save(user)

        val note1 = Note("note 1", NoteType.TEXT_NOTE, user)
        noteRepository.save(note1)

        val note2 = Note("note 2", NoteType.TASK_LIST, user)
        noteRepository.save(note2)

        val textNote = TextNote(note1, "text")
        textNoteRepository.save(textNote)

        val taskList = TaskList(note2)
        taskListRepository.save(taskList)

        val task = Task(taskList, "task 1")
        taskRepository.save(task)
    }
}
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
        val user1 = User("user1", bCryptPasswordEncoder.encode("password"))
        userRepository.save(user1)
        val user2 = User("user2", bCryptPasswordEncoder.encode("password"))
        userRepository.save(user2)

        val note1 = Note("text note 1", NoteType.TEXT_NOTE, user1)
        val note2 = Note("task list 1", NoteType.TASK_LIST, user1)
        val note3 = Note("text note 2", NoteType.TEXT_NOTE, user2)
        val note4 = Note("task list 2", NoteType.TASK_LIST, user2)

        val textNote1 = TextNote(note1)
        textNoteRepository.save(textNote1)
        val textNote2 = TextNote(note3)
        textNoteRepository.save(textNote2)

        val taskList1 = TaskList(note2)
        taskListRepository.save(taskList1)
        val taskList2 = TaskList(note4)
        taskListRepository.save(taskList2)

        val task1 = Task(taskList1, "task 1.1")
        taskRepository.save(task1)
        val task2 = Task(taskList1, "task 1.2")
        taskRepository.save(task2)
        val task3 = Task(taskList2, "task 2.1")
        taskRepository.save(task3)
        val task4 = Task(taskList2, "task 3.2")
        taskRepository.save(task4)
    }
}
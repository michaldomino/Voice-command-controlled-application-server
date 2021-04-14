package com.example.voicecommands.controllers.rest

import com.example.voicecommands.dto.model.NoteDTO
import com.example.voicecommands.dto.model.UserDTO
import com.example.voicecommands.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserRestController(
    private val userService: UserService,
) {

    @PostMapping
    fun registerUser(userDTO: UserDTO): ResponseEntity<UserDTO> {
        if (userDTO.id != null) {
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity<UserDTO>(userService.saveUser(userDTO), HttpStatus.CREATED)
    }

    @GetMapping("list")
    fun getAllUsers(): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok(userService.findAllUsers())
    }

    @GetMapping("{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<UserDTO> {
        val user = userService.findUserById(id)
        if (user == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(user)
    }

    @GetMapping("/search/findAllByUsernameContains")
    fun getUsersByUserNameContains(@RequestParam username: String): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok(userService.findAllUsersByUsernameContains(username))
    }

    @GetMapping("/search/findAllByOwnedNotesCountBetween")
    fun getUsersByOwnedNotesCountBetween(
        @RequestParam(required = false, defaultValue = "0") minimumOwnedNotesCount: Int,
        @RequestParam maximumOwnedNotesCount: Int? = null
    ): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok(
            userService.findAllUsersByOwnedNotesCountBetween(
                minimumOwnedNotesCount,
                maximumOwnedNotesCount
            )
        )
    }

    @PostMapping("/{id}/addSharedNotes")
    fun addSharedNotesToUser(
        @PathVariable id: String,
        @RequestBody noteIds: List<NoteDTO>
    ): ResponseEntity<List<NoteDTO>> {
        val user = userService.findUserById(id)
        if (user == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(userService.addSharedNoteToUser(id, noteIds))
    }

    @PutMapping("{id}")
    fun updateUser(@PathVariable id: String, @RequestBody userDTO: UserDTO): ResponseEntity<UserDTO> {
        val user = userService.findUserById(id)
        if (user == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(userService.updateUser(userDTO))
    }

    @PatchMapping("{id}")
    fun partialUpdateUser(@PathVariable id: String, @RequestBody userDTO: UserDTO): ResponseEntity<UserDTO> {
        val user = userService.findUserById(id)
        if (user == null) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(userService.partialUpdateUser(user, userDTO))
    }

    @DeleteMapping("{id}")
    fun deleteNote(@PathVariable id: String): ResponseEntity<Any> {
        if (userService.findUserById(id) == null) {
            return ResponseEntity.notFound().build()
        }
        userService.deleteUserById(id)
        return ResponseEntity.ok().build()
    }
}
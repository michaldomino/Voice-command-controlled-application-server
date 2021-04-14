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

    @GetMapping
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

    @GetMapping("/search/findAllBySharedNoteNameContain")
    fun getUsersByUserNameContains(@RequestParam sharedNoteName: String): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok(userService.findAllUsersBySharedNoteNameContains(sharedNoteName))
    }
}
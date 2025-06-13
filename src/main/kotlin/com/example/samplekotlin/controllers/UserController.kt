package com.example.samplekotlin.controllers

import com.example.samplekotlin.dto.UserCreateDTO
import com.example.samplekotlin.dto.UserResponseDTO
import com.example.samplekotlin.dto.UserUpdateDTO
import com.example.samplekotlin.services.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(
        @Valid @RequestBody userCreateDTO: UserCreateDTO,
        bindingResult: BindingResult
    ): ResponseEntity<Any> {
        if (bindingResult.hasErrors()) {
            val errors = bindingResult.fieldErrors.map {
                mapOf("field" to it.field, "message" to it.defaultMessage)
            }
            return ResponseEntity(mapOf("errors" to errors), HttpStatus.BAD_REQUEST)
        }

        return try {
            val createdUser = userService.createUser(userCreateDTO)
            ResponseEntity(createdUser, HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @Valid @RequestBody userUpdateDTO: UserUpdateDTO,
        bindingResult: BindingResult
    ): ResponseEntity<Any> {
        if (bindingResult.hasErrors()) {
            val errors = bindingResult.fieldErrors.map {
                mapOf("field" to it.field, "message" to it.defaultMessage)
            }
            return ResponseEntity(mapOf("errors" to errors), HttpStatus.BAD_REQUEST)
        }

        return try {
            val user = userService.updateUser(id, userUpdateDTO)
            if (user != null) {
                ResponseEntity.ok(user)
            } else {
                ResponseEntity(mapOf("error" to "User not found"), HttpStatus.NOT_FOUND)
            }
        } catch (e: IllegalArgumentException) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Any> {
        val deleted = userService.deleteUser(id)
        return if (deleted) {
            ResponseEntity(mapOf("message" to "User deleted successfully"), HttpStatus.OK)
        } else {
            ResponseEntity(mapOf("error" to "User not found"), HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponseDTO>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<Any> {
        val user = userService.getUserById(id)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity(mapOf("error" to "User not found"), HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/email/{email}")
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<Any> {
        val user = userService.getUserByEmail(email)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity(mapOf("error" to "User not found"), HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/name/{name}")
    fun getUserByName(@PathVariable name: String): ResponseEntity<Any> {
        val user = userService.getUserByName(name)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity(mapOf("error" to "User not found"), HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/check-email/{email}")
    fun isEmailTaken(@PathVariable email: String): ResponseEntity<Map<String, Boolean>> {
        val taken = userService.isEmailTaken(email)
        return ResponseEntity.ok(mapOf("taken" to taken))
    }

    @GetMapping("/exists/{id}")
    fun userExists(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val exists = userService.userExists(id)
        return ResponseEntity.ok(mapOf("exists" to exists))
    }

    @GetMapping("/count")
    fun getUserCount(): ResponseEntity<Map<String, Long>> {
        val count = userService.getUserCount()
        return ResponseEntity.ok(mapOf("count" to count))
    }
}
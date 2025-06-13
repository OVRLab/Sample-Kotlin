package com.example.samplekotlin.Repo

import com.example.samplekotlin.Model.User
import com.example.samplekotlin.Service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class HelloController(private val userService: UserService) {

    // search for specific user
    @GetMapping("/search")
    fun searchUsersByName(@RequestParam name: String): List<User> =
        userService.searchUsersByName(name)

    // get all users
    @GetMapping
    fun getAllUsers(): List<User> = userService.getAllUsers()

    // create user
    @PostMapping
    fun createUser(@RequestBody users: User): ResponseEntity<User> {
        val savedUser = userService.createUser(users)
        return ResponseEntity.ok(savedUser)
    }
    // update user by his ID
    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody user: User): ResponseEntity<User> {
        val updated = userService.updateUser(id, user)
        return if (updated != null) ResponseEntity.ok(updated)
        else ResponseEntity.notFound().build()
    }

    // delete user by his ID
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Void> {
        val deleted = userService.deleteUser(id)
        return if (deleted) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
    }

}

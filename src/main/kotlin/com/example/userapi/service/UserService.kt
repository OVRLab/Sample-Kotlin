package com.example.userapi.service

import com.example.userapi.model.User
import com.example.userapi.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun updateUser(id: Long, user: User): User {
        val existingUser = userRepository.findById(id).orElseThrow { NoSuchElementException("User not found") }
        val updatedUser = existingUser.copy(
            username = user.username,
            email = user.email,
            age = user.age,
            phoneNumber = user.phoneNumber
        )
        return userRepository.save(updatedUser)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUserById(id: Long): User {
        return userRepository.findById(id).orElseThrow { NoSuchElementException("User not found") }
    }

//    Extra Operation
    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw NoSuchElementException("User not found")
        }
        userRepository.deleteById(id)
    }
}
package com.example.samplekotlin.services

import com.example.samplekotlin.dto.UserCreateDTO
import com.example.samplekotlin.dto.UserResponseDTO
import com.example.samplekotlin.dto.UserUpdateDTO
import com.example.samplekotlin.entities.User
import com.example.samplekotlin.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
open class UserService(private val userRepository: UserRepository) {

    fun createUser(userCreateDTO: UserCreateDTO): UserResponseDTO {
        if (userRepository.existsByEmail(userCreateDTO.email)) {
            throw IllegalArgumentException("Email ${userCreateDTO.email} already exists")
        }
        
        val user = User(
            id = 0L, // Will be auto-generated
            name = userCreateDTO.name,
            email = userCreateDTO.email,
            age = userCreateDTO.age,
            phoneNumber = userCreateDTO.phoneNumber
        )
        
        val savedUser = userRepository.save(user)
        return savedUser.toResponseDTO()
    }

    fun updateUser(id: Long, userUpdateDTO: UserUpdateDTO): UserResponseDTO? {
        return if (userRepository.existsById(id)) {
            val existingUser = userRepository.findById(id).get()

            // Check if email is being changed and if new email already exists
            userUpdateDTO.email?.let { newEmail ->
                if (newEmail != existingUser.email && userRepository.existsByEmail(newEmail)) {
                    throw IllegalArgumentException("Email $newEmail already exists")
                }
            }

            val userToUpdate = User(
                id = id,
                name = userUpdateDTO.name ?: existingUser.name,
                email = userUpdateDTO.email ?: existingUser.email,
                age = userUpdateDTO.age ?: existingUser.age,
                phoneNumber = userUpdateDTO.phoneNumber ?: existingUser.phoneNumber
            )

            val savedUser = userRepository.save(userToUpdate)
            savedUser.toResponseDTO()
        } else {
            null
        }
    }

    fun deleteUser(id: Long): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    fun getAllUsers(): List<UserResponseDTO> {
        return userRepository.findAll().map { it.toResponseDTO() }
    }

    fun getUserById(id: Long): UserResponseDTO? {
        return userRepository.findById(id).orElse(null)?.toResponseDTO()
    }

    fun getUserByEmail(email: String): UserResponseDTO? {
        return userRepository.findByEmail(email)?.toResponseDTO()
    }

    fun getUserByName(name: String): UserResponseDTO? {
        return userRepository.findByName(name)?.toResponseDTO()
    }

    fun isEmailTaken(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun userExists(id: Long): Boolean {
        return userRepository.existsById(id)
    }

    fun getUserCount(): Long {
        return userRepository.count()
    }

    // Extension function to convert User entity to DTO
    private fun User.toResponseDTO(): UserResponseDTO {
        return UserResponseDTO(
            id = this.id,
            name = this.name,
            email = this.email,
            age = this.age,
            phoneNumber = this.phoneNumber
        )
    }
}
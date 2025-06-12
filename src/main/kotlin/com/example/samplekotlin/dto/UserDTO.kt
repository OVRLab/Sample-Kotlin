package com.example.samplekotlin.dto

import jakarta.validation.constraints.*

// For CREATE - all fields required
data class UserCreateDTO(
    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    val name: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email should be valid")
    @field:Size(max = 100, message = "Email cannot exceed 100 characters")
    val email: String,

    @field:NotNull(message = "Age cannot be null")
    @field:Min(value = 1, message = "Age must be at least 1")
    @field:Max(value = 120, message = "Age cannot exceed 120")
    val age: Int,

    @field:NotBlank(message = "Phone number cannot be blank")
    @field:Pattern(
        regexp = "^[+]?[0-9]{10,15}$", 
        message = "Phone number must be 10-15 digits and can start with +"
    )
    val phoneNumber: String
)

// For UPDATE - all fields optional (nullable)
data class UserUpdateDTO(
    @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    val name: String? = null,

    @field:Email(message = "Email should be valid")
    @field:Size(max = 100, message = "Email cannot exceed 100 characters")
    val email: String? = null,

    @field:Min(value = 1, message = "Age must be at least 1")
    @field:Max(value = 120, message = "Age cannot exceed 120")
    val age: Int? = null,

    @field:Pattern(
        regexp = "^[+]?[0-9]{10,15}$", 
        message = "Phone number must be 10-15 digits and can start with +"
    )
    val phoneNumber: String? = null
)

data class UserResponseDTO(
    val id: Long,
    val name: String,
    val email: String,
    val age: Int,
    val phoneNumber: String
)
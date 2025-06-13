package com.example.samplekotlin.Model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    var name: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    var email: String,

    @field:NotBlank(message = "Phone number cannot be blank")
    @field:Pattern(
        regexp = "^\\+?[0-9]{7,15}\$",
        message = "Invalid phone number format"
    )
    @Column(unique = true, nullable = false)
    var phoneNumber: String
)

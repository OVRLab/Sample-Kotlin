package com.example.samplekotlin.entities

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
data class User(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @Column(nullable = false)
    val name: String = "",

    @Column(nullable = false, unique = true)
    val email: String = "",

    @Column(nullable = false)
    val age: Int = 0,

    @Column(nullable = false)
    val phoneNumber: String = ""
)
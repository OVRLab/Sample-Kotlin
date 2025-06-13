package com.example.samplekotlin.Repo

import com.example.samplekotlin.Model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findByNameContainingIgnoreCase(name: String): List<User>
}


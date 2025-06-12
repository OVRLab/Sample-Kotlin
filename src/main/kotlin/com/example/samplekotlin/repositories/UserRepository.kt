package com.example.samplekotlin.repositories

import com.example.samplekotlin.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByName(name: String): User?

    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean
}
package com.example.samplekotlin.Service
import com.example.samplekotlin.Model.User
import com.example.samplekotlin.Repo.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    // create user
    fun createUser(user: User): User = userRepository.save(user)

    // update user
    fun updateUser(id: Int, updatedUser: User): User? {
        val users = userRepository.findById(id).orElse(null) ?: return null
        users.name = updatedUser.name
        users.email = updatedUser.email
        return userRepository.save(users)
    }

    //delete user
    fun deleteUser(id: Int): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    //get all users
    fun getAllUsers(): List<User> = userRepository.findAll()

    // search for user
    fun searchUsersByName(name: String): List<User> =
        userRepository.findByNameContainingIgnoreCase(name)

}

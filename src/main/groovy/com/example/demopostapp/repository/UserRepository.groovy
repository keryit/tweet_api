package com.example.demopostapp.repository

import com.example.demopostapp.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username)
}
package com.example.demopostapp.repository


import com.example.demopostapp.model.Post
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository extends MongoRepository<Post, String> {
    //@Query(value = "{'userId' : ?0}")
    List<Post> findAllByUserId(String userId)
}

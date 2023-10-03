package com.example.demopostapp.repository

import com.example.demopostapp.model.Comment
import com.example.demopostapp.model.Post
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByPost(Post post)
}

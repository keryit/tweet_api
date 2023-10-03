package com.example.demopostapp.repository

import com.example.demopostapp.model.Like
import com.example.demopostapp.model.Post
import org.springframework.data.mongodb.repository.MongoRepository

interface LikeRepository extends MongoRepository<Like, String> {
    List<Like> findAllByPost(Post post)
}
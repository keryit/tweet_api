package com.example.demopostapp.model

import com.example.demopostapp.exception.EntityNotFoundException
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "posts")
class Post {
    @Id
    String id
    String content
    String userId
    @DBRef
    List<Comment> comments = []
    @DBRef
    List<Like> likes = []

    void toggleLike(String userId) {
        def existingLike = likes.find { it.post.id == userId }

        if (existingLike) {
            likes.remove(existingLike)
        } else {
            def post = existingLike.post
            if (post) {
                def newLike = new Like(id: userId, post: post)
                likes.add(newLike)
            } else {
                throw new EntityNotFoundException("Post not found")
            }
        }
    }
}

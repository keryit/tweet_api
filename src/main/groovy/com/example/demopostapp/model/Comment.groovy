package com.example.demopostapp.model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "comments")
class Comment {
    @Id
    String id
    String content
    String userId
    @DBRef
    Post post
}

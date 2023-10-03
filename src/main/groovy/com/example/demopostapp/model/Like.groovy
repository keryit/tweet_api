package com.example.demopostapp.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Like {
    @Id
    String id
    @DBRef
    Post post
}

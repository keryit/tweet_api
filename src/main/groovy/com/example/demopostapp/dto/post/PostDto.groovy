package com.example.demopostapp.dto.post

import com.example.demopostapp.dto.comment.CommentDto
import com.example.demopostapp.dto.LikeDto

class PostDto {
    String id
    String content
    List<CommentDto> comments
    List<LikeDto> likes
}

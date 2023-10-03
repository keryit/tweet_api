package com.example.demopostapp.mapper

import com.example.demopostapp.dto.LikeDto
import com.example.demopostapp.dto.comment.CommentDto
import com.example.demopostapp.dto.post.PostDto
import com.example.demopostapp.dto.post.PostRequestDto
import com.example.demopostapp.model.Post
import org.springframework.stereotype.Component

@Component
class PostMapper {

    PostDto toDto(Post post) {
         def comments = post.getComments().collect {
             like -> new CommentDto(
                     id: like.id,
                     text: like.content
             )
         }
         def likes = post.getLikes().collect { like ->
             new LikeDto(id: like.id)
         }
        PostDto postDto = new PostDto(
                id: post.id,
                content: post.content,
                comments: comments,
                likes: likes
        )
        return postDto
    }

    Post toModel(PostRequestDto postDto) {
        Post post = new Post(
                content: postDto.content
        )
        return post
    }
}

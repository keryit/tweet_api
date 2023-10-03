package com.example.demopostapp.service

import com.example.demopostapp.dto.comment.CommentRequestDto
import com.example.demopostapp.dto.post.PostDto
import com.example.demopostapp.dto.post.PostRequestDto

interface PostService {
    PostDto save(PostRequestDto post)

    List<PostDto> findAllByUser()

    void editPost(String postId, String newContent)

    void deletePost(String postId)

    void toggleLike(String postId)

    void addCommentToPost(String postId, CommentRequestDto commentDto)
}

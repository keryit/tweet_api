package com.example.demopostapp.controller

import com.example.demopostapp.dto.comment.CommentRequestDto
import com.example.demopostapp.dto.post.PostDto
import com.example.demopostapp.dto.post.PostRequestDto
import com.example.demopostapp.service.PostService
import com.example.demopostapp.service.impl.FeedService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostController {

    @Autowired
    PostService postService
    @Autowired
    FeedService feedService

    @GetMapping
     List<PostDto> getAllPosts() {
        postService.findAllByUser()
    }

    @PostMapping
    PostDto createPost(@RequestBody PostRequestDto post) {
        postService.save(post)
    }

    @PutMapping("/{postId}")
    ResponseEntity<String> editPost(@PathVariable String postId, @RequestBody PostRequestDto request) {
        if (request.getContent()) {
            postService.editPost(postId, request.getContent())
            return ResponseEntity.status(HttpStatus.OK).body("Post edited successfully")
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New content is required")
        }
    }

    @DeleteMapping("/{postId}")
    ResponseEntity<String> deletePost(@PathVariable String postId) {
        postService.deletePost(postId)
        return ResponseEntity.status(HttpStatus.OK).body("Post deleted successfully")
    }

    @PostMapping("/{postId}/like/{userId}")
    ResponseEntity<String> likePost(@PathVariable String postId) {
        postService.toggleLike(postId)
        return ResponseEntity.status(HttpStatus.OK).body("Post liked/unliked successfully")
    }

    @PostMapping("/{postId}/comments")
    ResponseEntity<String> addCommentToPost(
            @RequestParam String postId,
            @RequestBody CommentRequestDto commentDto
    ) {
        postService.addCommentToPost(postId, commentDto)
        return ResponseEntity.status(HttpStatus.OK).body("Comment added successfully")
    }

    @GetMapping("/feed")
    List<PostDto> getFeedPosts() {
        feedService.getFeedPosts()
    }
}

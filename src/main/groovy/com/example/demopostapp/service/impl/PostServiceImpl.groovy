package com.example.demopostapp.service.impl

import com.example.demopostapp.dto.comment.CommentRequestDto
import com.example.demopostapp.dto.post.PostDto
import com.example.demopostapp.dto.post.PostRequestDto
import com.example.demopostapp.exception.EntityNotFoundException
import com.example.demopostapp.exception.PermissionProblemException
import com.example.demopostapp.mapper.PostMapper
import com.example.demopostapp.model.Comment
import com.example.demopostapp.model.Post
import com.example.demopostapp.model.User
import com.example.demopostapp.repository.CommentRepository
import com.example.demopostapp.repository.PostRepository
import com.example.demopostapp.repository.UserRepository
import com.example.demopostapp.service.PostService
import com.example.demopostapp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository
    @Autowired
    PostMapper postMapper
    @Autowired
    UserService userService
    @Autowired
    UserRepository userRepository
    @Autowired
    CommentRepository commentRepository

    @Override
    @Transactional
    PostDto save(PostRequestDto post) {
        User user = userService.getUser()
        Post createdPost = postMapper.toModel(post)
        createdPost.setUserId(user.getId())

        def savedPost = postRepository.save(createdPost)
        user.setPosts(List.of(savedPost))
        userRepository.save(user)

        return postMapper.toDto(savedPost)
    }

    @Override
    List<PostDto> findAllByUser() {
         postRepository.findAllByUserId(userService.getUser().getId())
        .stream()
        .map(postMapper::toDto)
        .toList()
    }

    @Override
    void editPost(String postId, String newContent) {
        User user = userService.getUser()
        def post = postRepository.findById(postId)
        if (!post.isPresent() || user.getId() != post.get().getUserId()) {
           throw new PermissionProblemException("You can not edit this post")
        }
        post.get().setContent(newContent)
        postRepository.save(post.get())
    }

    @Override
    void deletePost(String postId) {
        def post = postRepository.findById(postId)
        def user = userService.getUser()
        if (!post.isPresent() || user.getId() != post.get().getUserId()) {
            throw new PermissionProblemException("You can not delete this post")
        }
        postRepository.deleteById(postId)
    }

    @Transactional
    @Override
    void addCommentToPost(String postId, CommentRequestDto commentDto) {
        def post = postRepository.findById(postId).orElse(null)

        if (post == null) {
            throw new EntityNotFoundException("Post not found")
        }

        Comment comment = new Comment(
                content: commentDto.comment,
                post: post,
                userId: userService.getUser().getId()
                )

        commentRepository.save(comment)
        post.comments << comment
        postRepository.save(post)
    }

    @Override
    void toggleLike(String postId) {
        def user = userService.getUser()
        def post = postRepository.findById(postId)
        if (!post.isPresent()) {
          throw new EntityNotFoundException("There is no post with id " + postId)
        }

        def postEntity = post.get()
        postEntity.toggleLike(user.getId())
        postRepository.save(postEntity)
    }
}

package com.example.demopostapp.service.impl

import com.example.demopostapp.dto.post.PostDto
import com.example.demopostapp.mapper.PostMapper
import com.example.demopostapp.model.Post
import com.example.demopostapp.repository.CommentRepository
import com.example.demopostapp.repository.LikeRepository
import com.example.demopostapp.repository.PostRepository
import com.example.demopostapp.repository.UserRepository
import com.example.demopostapp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeedService {
    @Autowired
    UserRepository userRepository
    @Autowired
    PostRepository postRepository
    @Autowired
    UserService userService
    @Autowired
    CommentRepository commentRepository
    @Autowired
    LikeRepository likeRepository
    @Autowired
    PostMapper postMapper

    List<PostDto> getFeedPosts() {
        def user = userService.getUser()

        Set<String> following = user.subscriptions
        Set<String> subscribers = user.subscribers

        List<Post> feedPosts = []

        following.each { followingUserId ->
            def followingUser = userRepository.findById(followingUserId).orElse(null)
            if (followingUser) {
                feedPosts.addAll(followingUser.posts)
            }
        }

        subscribers.each { subscriberUserId ->
            def subscriberUser = userRepository.findById(subscriberUserId).orElse(null)
            if (subscriberUser) {
                feedPosts.addAll(subscriberUser.posts)
            }
        }

    feedPosts = feedPosts.findAll { it != null }.unique { it.id }

        return feedPosts.stream()
        .map(postMapper::toDto)
        .toList()
    }
}

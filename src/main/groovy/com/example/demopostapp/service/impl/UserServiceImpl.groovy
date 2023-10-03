package com.example.demopostapp.service.impl

import com.example.demopostapp.dto.user.UpdateUserDto
import com.example.demopostapp.dto.user.UserDto
import com.example.demopostapp.dto.user.UserRegisterDto
import com.example.demopostapp.exception.EmptyFieldException
import com.example.demopostapp.exception.EntityNotFoundException
import com.example.demopostapp.exception.RegistrationException
import com.example.demopostapp.mapper.UserMapper
import com.example.demopostapp.model.User
import com.example.demopostapp.repository.UserRepository
import com.example.demopostapp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository
    @Autowired
    UserMapper userMapper
    @Autowired
    PasswordEncoder passwordEncoder

    @Override
    User findByUsername(String username) {
        userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Can't find user by email"))
    }

    @Override
    User findById(String id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"))
    }

    @Override
    UserDto register(UserRegisterDto registrationRequest) throws RegistrationException {
        if (userRepository.findByUsername(registrationRequest.getEmail()).isPresent()) {
            throw new RegistrationException("This email already exist");
        }
        User user = userMapper.toModel(registrationRequest)
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()))
        User savedUser = userRepository.save(user)
        return userMapper.toDto(savedUser)
    }

    @Override
    UserDto updateUser(UpdateUserDto user) {
        User existingUser = getUser();

        if (existingUser == null) {
            throw new EntityNotFoundException("User not found")
        }

        existingUser.setUsername(user.getEmail())
        if (user.getPassword().isEmpty()) {
           throw new EmptyFieldException("Enter password to proceed with updating!")
        }
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()))
        User savedUser = userRepository.save(existingUser)
        return userMapper.toDto(savedUser)
    }

    @Override
    void delete(String userId) {
        User user = getUser()
        if (userId == user.getId()) {
            userRepository.delete(user)
        }
        throw new EntityNotFoundException("Can't find user by id " + userId)
    }

    @Transactional
    void subscribeUser(String targetUserId) {
        def currentUser = getUser()
        def targetUser = userRepository.findById(targetUserId).orElse(null)

        if (targetUser == null) {
            throw new EntityNotFoundException("User not found")
        }

        if (currentUser.subscriptions.contains(targetUserId)) {
            currentUser.subscriptions.remove(targetUserId)
            targetUser.subscribers.remove(currentUser.getId())
        } else {
            currentUser.subscriptions << targetUserId
            targetUser.subscribers << currentUser.getId()
        }

        userRepository.save(targetUser)
        userRepository.save(currentUser)
    }

    @Override
    User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
        return userRepository.findByUsername(authentication.getName()).orElseThrow(() ->
                new EntityNotFoundException("Can't find user by name"
                        + authentication.getName()))
    }
}

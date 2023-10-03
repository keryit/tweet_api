package com.example.demopostapp.service

import com.example.demopostapp.dto.user.UpdateUserDto
import com.example.demopostapp.dto.user.UserDto
import com.example.demopostapp.dto.user.UserRegisterDto
import com.example.demopostapp.exception.RegistrationException
import com.example.demopostapp.model.User

interface UserService {
    User findByUsername(String username)

    User findById(String id)

    UserDto register(UserRegisterDto registrationRequest)
            throws RegistrationException;

    UserDto updateUser(UpdateUserDto user)

    void delete(String userId)

    User getUser()

    void subscribeUser(String targetUserId)
}
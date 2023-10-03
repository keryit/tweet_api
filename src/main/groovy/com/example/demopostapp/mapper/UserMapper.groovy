package com.example.demopostapp.mapper

import com.example.demopostapp.dto.user.UpdateUserDto
import com.example.demopostapp.dto.user.UserDto
import com.example.demopostapp.dto.user.UserRegisterDto
import com.example.demopostapp.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(
                id: user.id,
                username: user.username
        )
    }

    User toModel(UserRegisterDto userRegisterDto) {
        return new User(
                username: userRegisterDto.email,
                password: userRegisterDto.password
        )
    }

    User updateUserToModel(UpdateUserDto request) {
        return new User(
                username: request.email,
                password: request.password
        )
    }
}

package com.example.demopostapp.controller

import com.example.demopostapp.dto.user.UserDto
import com.example.demopostapp.dto.user.UserLoginDto
import com.example.demopostapp.dto.user.UserLoginResponseDto
import com.example.demopostapp.dto.user.UserRegisterDto
import com.example.demopostapp.exception.RegistrationException
import com.example.demopostapp.security.AuthenticationService
import com.example.demopostapp.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    UserService userService
    @Autowired
    AuthenticationService authenticationService

    @PostMapping("/login")
    UserLoginResponseDto login(@RequestBody UserLoginDto requestDto) {
        return authenticationService.authenticate(requestDto)

    }

    @PostMapping("/register")
    UserDto register(@RequestBody @Valid
                             UserRegisterDto request)
            throws RegistrationException {
        return userService.register(request)
    }

    @GetMapping('/logout')
    void logout(HttpServletRequest request,
                HttpServletResponse response) {
     authenticationService.logout(request, response)
    }
}

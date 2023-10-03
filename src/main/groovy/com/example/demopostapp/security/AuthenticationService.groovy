package com.example.demopostapp.security

import com.example.demopostapp.dto.user.UserLoginDto
import com.example.demopostapp.dto.user.UserLoginResponseDto
import com.example.demopostapp.service.impl.JwtService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Service

@Service
class AuthenticationService {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    UserLoginResponseDto authenticate(UserLoginDto request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        )
        String token = jwtService.generateToken(authentication.getName())
        return new UserLoginResponseDto(token)
    }

    void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication()
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth)
        }
    }
}

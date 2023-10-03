package com.example.demopostapp.controller

import com.example.demopostapp.dto.user.UpdateUserDto
import com.example.demopostapp.dto.user.UserDto
import com.example.demopostapp.service.UserService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {
    @Autowired
    UserService userService

    @PutMapping("/update")
    UserDto updateUser(@RequestBody @Valid UpdateUserDto dto) {
        userService.updateUser(dto)
    }

    @PostMapping("/subscribe/{targetUserId}")
    ResponseEntity<String> subscribeUser(@PathVariable String targetUserId) {
        userService.subscribeUser(targetUserId)
        return ResponseEntity.status(HttpStatus.OK).body("Operation done")
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<String> delete(@PathVariable String userId) {
        userService.delete(userId)
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully")
    }
}

package com.example.demopostapp.dto.user

import jakarta.validation.constraints.Size

class UpdateUserDto {
    @Size(min = 4)
    String email
    @Size(min = 4)
    String password
}

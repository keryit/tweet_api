package com.example.demopostapp.dto.user

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class UserRegisterDto {
    @NotBlank
    @Size(min = 4)
    String email

    @NotBlank
    @Size(min = 4)
    String password
}

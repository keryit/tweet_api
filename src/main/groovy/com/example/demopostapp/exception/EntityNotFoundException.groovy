package com.example.demopostapp.exception

class EntityNotFoundException extends RuntimeException {
    EntityNotFoundException(String message) {
        super(message)
    }
}

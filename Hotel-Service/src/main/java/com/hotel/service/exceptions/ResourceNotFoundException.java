package com.hotel.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Resource Not Found in DB");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

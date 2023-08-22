package com.microservice.user.service.payload;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;

}

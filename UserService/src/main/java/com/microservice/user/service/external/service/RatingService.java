package com.microservice.user.service.external.service;

import com.microservice.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("RATING-SERVICE")
public interface RatingService {

    @PostMapping("ratings")
    Rating create( Rating rating);
    @GetMapping("ratings/users/{userId}")
    List<Rating> getAllByUserId(@PathVariable String userId);
    @GetMapping("ratings/hotels/{hotelId}")
    List<Rating> getAllByHotelId(@PathVariable String hotelId);
    @GetMapping("ratings")
    List<Rating> getAll();
}

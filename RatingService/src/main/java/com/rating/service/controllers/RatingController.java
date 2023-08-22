package com.rating.service.controllers;

import com.rating.service.entities.Rating;
import com.rating.service.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        Rating createdRating = this.ratingService.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAll() {
        List<Rating> ratings = this.ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllByUserId(@PathVariable String userId) {
        List<Rating> ratings = this.ratingService.getAllRatingByUserId(userId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllByHotelId(@PathVariable String hotelId) {
        List<Rating> ratings = this.ratingService.getAllRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratings);
    }

//    @GetMapping("/{ratingId}")
//    public ResponseEntity <Rating> getAllByHotelId(@PathVariable String ratingId){
//        Rating rating =this.ratingService.get
//        return  ResponseEntity.status(HttpStatus.FOUND).body(ratings);
//    }


}

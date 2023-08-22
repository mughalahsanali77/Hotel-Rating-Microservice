package com.rating.service.services;

import com.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    List<Rating> getAllRatings();
    List<Rating> getAllRatingByUserId(String userId);
    List<Rating> getAllRatingByHotelId(String hotelId);
}

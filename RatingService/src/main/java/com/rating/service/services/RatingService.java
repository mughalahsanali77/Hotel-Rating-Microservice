package com.rating.service.services;

import com.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    Rating findById(String id);
    List<Rating> getAllRatings();
    Rating update(String id,Rating rating);

    Rating delete(String id);
    List<Rating> getAllRatingByUserId(String userId);
    List<Rating> getAllRatingByHotelId(String hotelId);
}

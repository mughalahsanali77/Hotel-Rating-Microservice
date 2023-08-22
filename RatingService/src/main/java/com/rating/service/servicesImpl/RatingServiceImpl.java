package com.rating.service.servicesImpl;

import com.rating.service.entities.Rating;
import com.rating.service.repository.RatingRepository;
import com.rating.service.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    public  RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository=ratingRepository;
    }

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}

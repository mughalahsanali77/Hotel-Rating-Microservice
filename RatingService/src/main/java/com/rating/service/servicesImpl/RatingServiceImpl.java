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
    public Rating findById(String id) {
        Rating rating=this.ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating Not Found With id : "+id));
        return rating;
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating update(String id,Rating rating) {
        Rating ratingFromDb=this.ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating Not Found With id : "+id));
        ratingFromDb.setRating(rating.getRating());
        ratingFromDb.setFeedback(rating.getFeedback());
        ratingFromDb.setHotelId(rating.getHotelId());
        ratingFromDb.setUserId(rating.getUserId());
        return this.ratingRepository.save(ratingFromDb);
    }

    @Override
    public Rating delete(String id) {
        Rating ratingFromDb=this.ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating Not Found With id : "+id));
        this.ratingRepository.delete(ratingFromDb);
        return ratingFromDb;
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

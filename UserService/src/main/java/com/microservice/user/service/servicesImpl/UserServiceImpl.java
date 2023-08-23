package com.microservice.user.service.servicesImpl;

import com.microservice.user.service.entities.Hotel;
import com.microservice.user.service.entities.Rating;
import com.microservice.user.service.entities.User;
import com.microservice.user.service.exceptions.ResourceNotFoundException;
import com.microservice.user.service.external.service.HotelService;
import com.microservice.user.service.external.service.RatingService;
import com.microservice.user.service.repositories.UserRepository;
import com.microservice.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;
    private final RatingService ratingService;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate,HotelService hotelService, RatingService ratingService) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.hotelService=hotelService;
        this.ratingService=ratingService;
    }

    @Override
    public User saveUser(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return this.userRepository.save(user);
    }




    /*   USING RestTemplate for communicating  with other project

    ----> * BEFORE USING Project Name instead of Localhost and port  check the bean of RestTemplate that should be annoted wit @LoadBalanced

    @Override
    public List<User> getAllUsers() {

        List<User> users = this.userRepository.findAll();
        users.forEach(user -> {
            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            logger.info("{ " + ratingsOfUser + "}");
            List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
            List<Rating> list=ratings.stream().map(rating -> {
                Hotel hotel=restTemplate.getForObject("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());

            user.setRatings(list);
        });
        return users;
    }

 *
 *
   ---> * BEFORE USING Project Name instead of Localhost and port  check the bean of RestTemplate that should be annoted wit @LoadBalanced

    @Override
    public User getUser(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with UserId : " + userId));

        //http://localhost:8083/ratings/users/6749cb84-5621-4643-a0a8-157a635784bb <- this is static url
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{ " + ratingsOfUser + "}");
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> list = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("Response Status Code : " + forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(list);
        return user;
    }
    *
    *
    */



    @Override
    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        users.forEach(user -> {
            List<Rating> ratings = ratingService.getAllByUserId(user.getUserId());
            List<Rating> list=ratings.stream().map(rating -> {
                Hotel hotel=hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());

            user.setRatings(list);
        });
        return users;
    }


    /**
     *
     * @param userId
     * @return User with Ratings and Hotel
     * @Description Using Feign Client Service
     */
    @Override
    public User getUser(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with UserId : " + userId));
        List<Rating> ratings = ratingService.getAllByUserId(user.getUserId());
        List<Rating> list = ratings.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(list);
        return user;
    }




    @Override
    public User updateUser(User user, String userId) {
        User userFromDb = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with UserId : " + userId));
        user.setUserId(userId);
        userFromDb.setName(user.getName());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setAbout(user.getAbout());

        return this.userRepository.save(userFromDb);
    }

    @Override
    public User deleteUser(String userId) {
        User userFromDb = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with UserId : " + userId));
        this.userRepository.delete(userFromDb);
        return userFromDb;
    }
}

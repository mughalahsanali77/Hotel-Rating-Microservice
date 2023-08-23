package com.microservice.user.service.external.service;

import com.microservice.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("HOTEL-SERVICE")
public interface HotelService {

    @PostMapping("hotel")
    ResponseEntity<Hotel> create(Hotel hotel);
    @GetMapping("hotel/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

    @GetMapping("hotel")
    List<Hotel> getAll();

    @PutMapping("hotel/{hotelId}")
    Hotel update(@PathVariable String hotelId,Hotel hotel);

    @DeleteMapping("hotel/{hotelId}")
    Hotel delete(@PathVariable String hotelId);

}

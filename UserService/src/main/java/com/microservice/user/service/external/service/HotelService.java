package com.microservice.user.service.external.service;

import com.microservice.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("HOTEL-SERVICE")
public interface HotelService {

    @PostMapping("hotels")
    ResponseEntity<Hotel> create(Hotel hotel);
    @GetMapping("hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

    @GetMapping("hotels")
    List<Hotel> getAll();

    @PutMapping("hotels/{hotelId}")
    Hotel update(@PathVariable String hotelId,Hotel hotel);

    @DeleteMapping("hotels/{hotelId}")
    Hotel delete(@PathVariable String hotelId);

}

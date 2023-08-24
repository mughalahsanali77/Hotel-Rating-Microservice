package com.hotel.service.controllers;

import com.hotel.service.entities.Hotel;
import com.hotel.service.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;
    public HotelController(HotelService hotelService){
        this.hotelService=hotelService;
    }

    @PostMapping
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel){
        Hotel createdHotel= this.hotelService.create(hotel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        Hotel hotel=hotelService.get(hotelId);
        return  ResponseEntity.ok(hotel);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        List<Hotel> hotels=hotelService.getAll();
        return ResponseEntity.ok(hotels);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> update(@PathVariable String hotelId,@RequestBody Hotel hotel){
        Hotel updatedHotel=hotelService.update(hotel,hotelId);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Hotel> delete(@PathVariable String hotelId){
        Hotel hotel=hotelService.delete(hotelId);
        return new ResponseEntity<>(hotel,HttpStatus.GONE);
    }


}

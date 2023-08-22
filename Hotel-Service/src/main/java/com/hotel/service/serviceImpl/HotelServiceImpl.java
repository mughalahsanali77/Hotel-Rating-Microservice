package com.hotel.service.serviceImpl;

import com.hotel.service.entities.Hotel;
import com.hotel.service.exceptions.ResourceNotFoundException;
import com.hotel.service.repositories.HotelRepository;
import com.hotel.service.services.HotelService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    public HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }

    @Override
    public Hotel create(Hotel hotel) {
        String randomId= UUID.randomUUID().toString();
        hotel.setId(randomId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
       return this.hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel Not Found With Given Id : "+id));
    }

    @Override
    public Hotel update(Hotel hotel, String id) {
        Hotel hotelFromDb=this.hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel Not Found With Given Id : "+id));
        hotelFromDb.setName(hotel.getName());
        hotelFromDb.setAbout(hotel.getAbout());
        hotelFromDb.setLocation(hotel.getLocation());
        return this.hotelRepository.save(hotelFromDb);
    }

    @Override
    public Hotel delete(String id) {
        Hotel hotelFromDb=this.hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel Not Found With Given Id : "+id));
        this.hotelRepository.delete(hotelFromDb);
        return hotelFromDb;
    }
}

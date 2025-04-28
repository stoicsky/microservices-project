package com.lcwd.hotel.services;


import com.lcwd.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create

    Hotel create(Hotel hotel);

    //getAll

    List<Hotel> getAllHotel();

    //get single
    Hotel get(String id);
}

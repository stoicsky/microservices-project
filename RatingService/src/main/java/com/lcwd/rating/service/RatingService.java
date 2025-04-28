package com.lcwd.rating.service;


import com.lcwd.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {


    //create rating

    Rating create(Rating rating);

    //get all ratings

    List<Rating> getAllRatings();

    //get user wise rating by taking userId
    List<Rating> getRatingByUserId(String userId);


    //get hotel wise rating by taking hotelId
    List<Rating> getRatingByHotelId(String hotelId);

}

package com.lcwd.rating.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Rating {

    @Id
    private String ratingId;
    private String userId;
    @Column(name="hotel_id")
    private String hotelId;
    private int rating;
    private String feedback;

    public void setHotelId(String hotelId){
        this.hotelId=hotelId;
    }
    public String getHotelId(){
        return hotelId;
    }


}

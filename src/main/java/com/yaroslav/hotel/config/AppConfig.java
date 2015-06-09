package com.yaroslav.hotel.config;

import com.yaroslav.hotel.Hotel;
import com.yaroslav.hotel.entity.Reservation;
import com.yaroslav.hotel.service.HotelRoomService;
import com.yaroslav.hotel.service.Reception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by employee on 6/5/15.
 */

@Configuration
@ComponentScan(basePackages = "com.yaroslav.hotel")
@Import(DatabaseConfig.class)
public class AppConfig {

//    @Bean(name = "hotel")
//    @Autowired
//    public Hotel getHotel(HotelRoomService hotelRoomService, Reception reception) {
//        return new Hotel(hotelRoomService, reception);
//    }
}

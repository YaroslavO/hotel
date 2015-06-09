package com.yaroslav.hotel;

import com.yaroslav.hotel.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by PC on 08.06.2015.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Hotel hotel = (Hotel) context.getBean("hotel");

        System.out.println(hotel.getAllHotelRoom());

        System.out.println(hotel.someWork());
    }
}

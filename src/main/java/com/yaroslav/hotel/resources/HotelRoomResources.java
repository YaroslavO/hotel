package com.yaroslav.hotel.resources;

import com.yaroslav.hotel.entity.HotelRoom;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdpdp on 29.06.2015.
 */
public class HotelRoomResources extends ResourceSupport {
    private List<HotelRoom> rooms = new ArrayList<>();

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
    }
}

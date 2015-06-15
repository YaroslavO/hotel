package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.HqlQueryHotelRoomSearchBuilder;
import com.yaroslav.hotel.exception.SearchNullParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by PC on 05.06.2015.
 */

@Service
@Transactional
public class HotelRoomServiceImpl implements HotelRoomService {

    private HotelRoomDao hotelRoom;

    public HotelRoomServiceImpl() {
    }

    @Autowired
    public HotelRoomServiceImpl(HotelRoomDao hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    @Override
    public List<HotelRoom> getAllRoom() {
        return hotelRoom.getAllHotelRoom();
    }

    @Override
    public void addHotelRoom(HotelRoom room) {
        hotelRoom.saveHotelRoom(room);
    }

    @Override
    public HotelRoom getHotelRoomById(Integer id) {
        return hotelRoom.getHotelRoomById(id);
    }

    @Override
    public List<HotelRoom> searchHotelRoomByParameter(HqlQueryHotelRoomSearchBuilder hqlQueryHotelRoomSearchBuilder) throws SearchNullParameterException {
        if (hqlQueryHotelRoomSearchBuilder == null) {
            throw new SearchNullParameterException();
        }

        return hotelRoom.searchHotelRoomByParameter(hqlQueryHotelRoomSearchBuilder);
    }

    @Override
    public void delete(HotelRoom room) {
        hotelRoom.deleteHotelRoom(room);
    }
}

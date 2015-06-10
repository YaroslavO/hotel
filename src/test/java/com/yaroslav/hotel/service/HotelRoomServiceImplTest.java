package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.entity.BudgetRoomType;
import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Parameter;
import com.yaroslav.hotel.entity.SizeRoomType;
import com.yaroslav.hotel.exception.SearchNullParameterException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by employee on 6/10/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class HotelRoomServiceImplTest {

    @Mock
    private HotelRoomDao hotelRoomDao;

    private HotelRoomService hotelRoomService;

    @Before
    public void setUp() throws Exception {
        hotelRoomService = new HotelRoomServiceImpl(hotelRoomDao);

        when(hotelRoomDao.saveHotelRoom(any(HotelRoom.class))).thenAnswer(new Answer<HotelRoom>() {

            @Override
            public HotelRoom answer(InvocationOnMock invocationOnMock) throws Throwable {
                HotelRoom hotelRoom = (HotelRoom) invocationOnMock.getArguments()[0];
                hotelRoom.setId(1);
                return hotelRoom;
            }
        });

        when(hotelRoomDao.getAllHotelRoom()).thenReturn(new ArrayList<HotelRoom>());

        when(hotelRoomDao.getHotelRoomById(1)).thenAnswer(new Answer<HotelRoom>() {

            @Override
            public HotelRoom answer(InvocationOnMock invocationOnMock) throws Throwable {
                Integer id = (Integer) invocationOnMock.getArguments()[0];
                HotelRoom hotelRoom = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.ECONOM);
                hotelRoom.setId(id);
                return hotelRoom;
            }
        });

        when(hotelRoomDao.getHotelRoomById(12)).thenReturn(null);
        when(hotelRoomDao.searchHotelRoomByParameter(any(Parameter.class)))
                .thenAnswer(new Answer<List<HotelRoom>>() {
                                @Override
                                public List<HotelRoom> answer(InvocationOnMock invocationOnMock) throws Throwable {
                                    return new ArrayList<HotelRoom>();
                                }
                            }
                );
    }

    @Test
    public void checkUpdateHotelRoom() throws Exception {
        //given
        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.ECONOM);

        //when
        hotelRoomService.addHotelRoom(hotelRoom);

        //then
        verify(hotelRoomDao).saveHotelRoom(hotelRoom);

        assertThat(hotelRoom.getId(), is(1));
    }

    @Test
    public void getAllHotelRoom() throws Exception {
        // when
        List<HotelRoom> rooms = hotelRoomService.getAllRoom();

        // then
        verify(hotelRoomDao).getAllHotelRoom();
        assertThat(rooms, not(comparesEqualTo(null)));
    }

    @Test
    public void getExistingHotelRoom() throws Exception {
        // when
        HotelRoom room = hotelRoomService.getHotelRoomById(1);

        // then
        verify(hotelRoomDao).getHotelRoomById(1);
        assertThat(room, Matchers.not(comparesEqualTo(null)));
        assertThat(room.getId(), is(1));
    }

    @Test
    public void getNotExistingHotelRoom() throws Exception {
        // when
        HotelRoom room = hotelRoomService.getHotelRoomById(12);

        // then
        verify(hotelRoomDao).getHotelRoomById(12);
        assertThat(room, Matchers.is(comparesEqualTo(null)));
    }
}
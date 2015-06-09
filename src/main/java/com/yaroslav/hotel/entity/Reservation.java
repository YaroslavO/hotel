package com.yaroslav.hotel.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by employee on 6/8/15.
 */

@Entity
@Table(name = "reservations")
public class Reservation implements Comparable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "fk_room")
    private HotelRoom hotelRoom;

    public Reservation(Period period) {
        this.startDate = period.begin;
        this.endDate = period.end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public void setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    @Override
    public int compareTo(Object o) {
        Reservation otherReservation = (Reservation) o;
        Date firstStartDate = setHourMinuteSecondMillisecondInZero(startDate);
        Date secondStartDate = setHourMinuteSecondMillisecondInZero(otherReservation.startDate);
        Date firstEndDate = setHourMinuteSecondMillisecondInZero(endDate);
        Date secondEndDate = setHourMinuteSecondMillisecondInZero(otherReservation.endDate);

        if (firstStartDate.compareTo(secondStartDate) != 0) {
            return firstStartDate.compareTo(secondStartDate);
        }

        if (firstEndDate.compareTo(secondEndDate) != 0) {
            return firstEndDate.compareTo(secondEndDate);
        }

        return 0;
    }

    private Date setHourMinuteSecondMillisecondInZero(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date newDate = calendar.getTime();
        return newDate;
    }

    @Override
    public boolean equals(Object obj) {
        Reservation otherReservation = (Reservation) obj;
        Date firstStartDate = setHourMinuteSecondMillisecondInZero(startDate);
        Date secondStartDate = setHourMinuteSecondMillisecondInZero(otherReservation.startDate);
        Date firstEndDate = setHourMinuteSecondMillisecondInZero(endDate);
        Date secondEndDate = setHourMinuteSecondMillisecondInZero(otherReservation.endDate);

        return firstStartDate.equals(secondStartDate) && firstEndDate.equals(secondEndDate);
    }
}

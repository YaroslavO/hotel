package com.yaroslav.hotel.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by employee on 6/5/15.
 */

@Entity
@Table(name = "date_reservation")
public class DateReservation implements Comparable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToMany(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "room_to_reservation",
            joinColumns = {@JoinColumn(name = "fk_date_reservation", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_room", referencedColumnName = "id")})
    private List<HotelRoom> room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<HotelRoom> getRoom() {
        return room;
    }

    public void setRoom(List<HotelRoom> room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Object o) {
        DateReservation otherDateReservation = (DateReservation) o;
        Date thisDate = setHourMinuteSecondMillisecondInZero(date);
        Date otherDate = setHourMinuteSecondMillisecondInZero(otherDateReservation.date);
        return thisDate.compareTo(otherDate);
    }

    private Date setHourMinuteSecondMillisecondInZero(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date newDate = calendar.getTime();
        return newDate;
    }
}

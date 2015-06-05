package com.yaroslav.hotel.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by employee on 6/5/15.
 */
@Entity
public class HotelRoom {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int Integer;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "fk_hotel")
    private Hotel hotel;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TypeHotelRoom type;

    @Column(name = "class", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ClassHotelRoom classRoom;

    @OneToMany(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "room_to_reservation",
            joinColumns = {@JoinColumn(name = "fk_room", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_date_reservation", referencedColumnName = "id")})
    private List<DateReservation> reservationPeriod;

    public int getInteger() {
        return Integer;
    }

    public void setInteger(int integer) {
        Integer = integer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public TypeHotelRoom getType() {
        return type;
    }

    public void setType(TypeHotelRoom type) {
        this.type = type;
    }

    public ClassHotelRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassHotelRoom classRoom) {
        this.classRoom = classRoom;
    }

    public List<DateReservation> getReservationPeriod() {
        return reservationPeriod;
    }

    public void setReservationPeriod(List<DateReservation> reservationPeriod) {
        this.reservationPeriod = reservationPeriod;
    }
}

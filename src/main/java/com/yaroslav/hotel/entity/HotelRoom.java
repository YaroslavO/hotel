package com.yaroslav.hotel.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by employee on 6/5/15.
 */
@Entity
@Table(name = "hotel_room")
public class HotelRoom {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SizeRoomType type;

    @Column(name = "class", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private BudgetRoomType classRoom;

    @OneToMany(mappedBy = "hotelRoom", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reservation> reservation;

    public HotelRoom() {
    }

    public HotelRoom(SizeRoomType type, BudgetRoomType classRoom) {
        this.type = type;
        this.classRoom = classRoom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SizeRoomType getType() {
        return type;
    }

    public void setType(SizeRoomType type) {
        this.type = type;
    }

    public BudgetRoomType getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(BudgetRoomType classRoom) {
        this.classRoom = classRoom;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }
}

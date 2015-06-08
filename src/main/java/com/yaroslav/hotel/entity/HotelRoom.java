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
    private Integer id;

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

    public HotelRoom() {
    }

    public HotelRoom(TypeHotelRoom type, ClassHotelRoom classRoom) {
        this.type = type;
        this.classRoom = classRoom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

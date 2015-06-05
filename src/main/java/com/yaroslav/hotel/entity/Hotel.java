package com.yaroslav.hotel.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by employee on 6/5/15.
 */

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "rooms")
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<HotelRoom> rooms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
    }
}

package com.yaroslav.hotel.entity;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "\t" + id + " | type = " + type.toString() +
                " | class = " + classRoom.toString();
    }
}

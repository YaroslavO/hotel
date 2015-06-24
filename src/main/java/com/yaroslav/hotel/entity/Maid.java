package com.yaroslav.hotel.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by employee on 6/24/15.
 */
@Entity
@Table(name = "maid")
public class Maid {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "maid_date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "fk_reservation")
    private Reservation reservation;

    public Maid() {
    }

    public Maid(Date date, Reservation reservation) {
        this.date = date;
        this.reservation = reservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}

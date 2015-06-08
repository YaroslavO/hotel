package com.yaroslav.hotel.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by employee on 6/8/15.
 */
public class Period {
    public Date begin = new Date();
    public Date end = new Date();

    public Period(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        this.begin.setTime(calendar.getTime().getTime());

        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        this.end.setTime(calendar.getTime().getTime());
    }

    public Period(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }
}

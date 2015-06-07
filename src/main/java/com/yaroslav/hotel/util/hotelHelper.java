package com.yaroslav.hotel.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by PC on 06.06.2015.
 */
public class hotelHelper {

    public static Date getDateWithHourMinuteSecondsMilisecondInZero(Calendar calendar) {
        Calendar newDate = Calendar.getInstance();
        newDate.setTime(calendar.getTime());
        newDate.set(Calendar.HOUR, 0);
        newDate.set(Calendar.MINUTE, 0);
        newDate.set(Calendar.SECOND, 0);
        newDate.set(Calendar.MILLISECOND, 0);
        return newDate.getTime();
    }
}

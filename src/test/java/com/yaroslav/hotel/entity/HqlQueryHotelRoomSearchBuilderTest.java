package com.yaroslav.hotel.entity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.*;

/**
 * Created by employee on 6/10/15.
 */

public class HqlQueryHotelRoomSearchBuilderTest {

    private HqlQueryHotelRoomSearchBuilder hqlBuilder;

    private static String selectQuery = "select HR from HotelRoom HR where ";

    private static String equalBudgetRoomTypeQuery = "HR.classRoom = :classRoom  and ";

    private static String notEqualBudgetRoomTypeQuery = "HR.classRoom <> :classRoom  and ";

    private static String sizeHotelRoomQuery = "HR.type = :sizeRoom and ";

    private static String partQueryWithPeriod = "( not exists " +
            "(from Reservation R where R.hotelRoom = HR and " +
            "(R.startDate between :startDate and :endDate or " +
            "R.endDate between :startDate and :endDate)))";

    @Before
    public void setUp() throws Exception {
        hqlBuilder = new HqlQueryHotelRoomSearchBuilder();
        Period period = createPeriod();
        hqlBuilder.setPeriod(period);
    }

    @Test
    public void generateQueryWithPeriod() throws Exception {
        String query = hqlBuilder.generateQuery();
        String allTestQuery = selectQuery + partQueryWithPeriod;

        assertThat(query, Matchers.not(comparesEqualTo(null)));
        assertThat(query, not(""));
        assertThat(query, Matchers.containsString(selectQuery));
        assertThat(query, containsString(partQueryWithPeriod));
        assertThat(query, is(allTestQuery));
    }

    @Test
    public void generateQueryWithNullPeriod() throws Exception {
        hqlBuilder.setPeriod(null);

        String query = hqlBuilder.generateQuery();

    }

    private Period createPeriod() {
        Date startDate = createDate(Calendar.JUNE, 2);
        Date endDate = createDate(Calendar.JUNE, 3);
        return new Period(startDate, endDate);
    }

    private Date createDate(int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, month, date);
        return calendar.getTime();
    }

    @Test
    public void generateQueryWithPeriodAndEqualBudgetRoomType() throws Exception {
        hqlBuilder.setBudgetRoomType(BudgetRoomType.ECONOM);
        String query = hqlBuilder.generateQuery();
        String allQuery = selectQuery + equalBudgetRoomTypeQuery + partQueryWithPeriod;

        assertThat(query, Matchers.containsString(equalBudgetRoomTypeQuery));
        assertThat(query, is(allQuery));
    }

    @Test
    public void generateQueryWithPeriodAndNotEqualBudgetRoomType() throws Exception {
        hqlBuilder.setNotThisBudgetRoomType(BudgetRoomType.ECONOM);
        String query = hqlBuilder.generateQuery();
        String allQuery = selectQuery + notEqualBudgetRoomTypeQuery + partQueryWithPeriod;

        assertThat(query, Matchers.containsString(notEqualBudgetRoomTypeQuery));
        assertThat(query, is(allQuery));
    }

    @Test
    public void generateQueryWithPeriodAndSizeHotelRoom() throws Exception {
        hqlBuilder.setSizeRoomType(SizeRoomType.DBL);
        String query = hqlBuilder.generateQuery();
        String allQuery = selectQuery + sizeHotelRoomQuery + partQueryWithPeriod;

        assertThat(query, Matchers.containsString(sizeHotelRoomQuery));
        assertThat(query, is(allQuery));
    }

    @Test
    public void generateDifficultQueryWithPeriodAndSizeAndNotEqualBudgetRoomType() throws Exception {
        hqlBuilder.setNotThisBudgetRoomType(BudgetRoomType.ECONOM);
        hqlBuilder.setSizeRoomType(SizeRoomType.DBL);
        String query = hqlBuilder.generateQuery();
        String sizeAndBudgetTypeQuery = notEqualBudgetRoomTypeQuery + sizeHotelRoomQuery;
        String allQuery = selectQuery + sizeAndBudgetTypeQuery + partQueryWithPeriod;

        assertThat(query, Matchers.containsString(sizeAndBudgetTypeQuery));
        assertThat(query, is(allQuery));
    }

    @Test
    public void generateDifficultQueryWithPeriodAndSizeAndEqualBudgetRoomType() throws Exception {
        hqlBuilder.setBudgetRoomType(BudgetRoomType.ECONOM);
        hqlBuilder.setSizeRoomType(SizeRoomType.DBL);
        String query = hqlBuilder.generateQuery();
        String sizeAndBudgetTypeQuery = equalBudgetRoomTypeQuery + sizeHotelRoomQuery;
        String allQuery = selectQuery + sizeAndBudgetTypeQuery + partQueryWithPeriod;

        assertThat(query, Matchers.containsString(sizeAndBudgetTypeQuery));
        assertThat(query, is(allQuery));
    }
}
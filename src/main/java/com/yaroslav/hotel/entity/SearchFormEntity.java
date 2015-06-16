package com.yaroslav.hotel.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by employee on 6/15/15.
 */
public class SearchFormEntity {

    private String startDate;
    private String endDate;
    private Boolean sizeSGL;
    private Boolean sizeDBL;
    private Boolean budgetTypeLux;
    private Boolean budgetTypeEconom;
    private Boolean budgetTypeStandard;

    public SearchFormEntity() {
        sizeSGL = false;
        sizeDBL = false;
        budgetTypeStandard = false;
        budgetTypeEconom = false;
        budgetTypeLux = false;
        startDate = "";
        endDate = "";
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public Boolean getSizeSGL() {
        return sizeSGL;
    }

    public void setSizeSGL(Boolean sizeSGL) {
        this.sizeSGL = sizeSGL;
    }

    public Boolean getSizeDBL() {
        return sizeDBL;
    }

    public void setSizeDBL(Boolean sizeDBL) {
        this.sizeDBL = sizeDBL;
    }

    public Boolean getBudgetTypeLux() {
        return budgetTypeLux;
    }

    public void setBudgetTypeLux(Boolean budgetTypeLux) {
        this.budgetTypeLux = budgetTypeLux;
    }

    public Boolean getBudgetTypeEconom() {
        return budgetTypeEconom;
    }

    public void setBudgetTypeEconom(Boolean budgetTypeEconom) {
        this.budgetTypeEconom = budgetTypeEconom;
    }

    public Boolean getBudgetTypeStandard() {
        return budgetTypeStandard;
    }

    public void setBudgetTypeStandard(Boolean budgetTypeStandard) {
        this.budgetTypeStandard = budgetTypeStandard;
    }

    public HqlQueryHotelRoomSearchBuilder createHQLBuilderForThisFormEntity() {
        HqlQueryHotelRoomSearchBuilder hqlBuilder = new HqlQueryHotelRoomSearchBuilder();
        Period period = createPeriodFromStringDate();
        hqlBuilder.setPeriod(period);
        hqlBuilder.setSizeRoomType(parseSizeParam());
        hqlBuilder.setBudgetRoomType(parseBudgetParam());
        hqlBuilder.setThisBudgetRoomType(checkRequestParam());

        return hqlBuilder;
    }

    private Period createPeriodFromStringDate() {
        return new Period(parseStringDate(startDate), parseStringDate(endDate));
    }

    private Date parseStringDate(String startDate) {
        DateFormat formatter = new SimpleDateFormat("yy-mm-dd");

        try {
            return formatter.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String formatToStringWithDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("yy-mm-dd");

        return formatter.format(date);
    }

    private SizeRoomType parseSizeParam() {
        if (sizeDBL && sizeSGL) {
            return null;
        }

        return sizeSGL
                ? SizeRoomType.SGL
                : sizeDBL
                ? SizeRoomType.DBL
                : null;
    }

    private BudgetRoomType parseBudgetParam() {
        if (budgetTypeLux && budgetTypeEconom && budgetTypeStandard) {
            return null;
        }

        if (budgetTypeLux && budgetTypeEconom) {
            return BudgetRoomType.STANDARD;
        }

        if (budgetTypeLux && budgetTypeStandard) {
            return BudgetRoomType.ECONOM;
        }

        if (budgetTypeEconom && budgetTypeStandard) {
            return BudgetRoomType.LUX;
        }

        if (budgetTypeLux) {
            return BudgetRoomType.LUX;
        }

        if (budgetTypeEconom) {
            return BudgetRoomType.ECONOM;
        }

        if (budgetTypeStandard) {
            return BudgetRoomType.STANDARD;
        }

        return null;
    }

    private Boolean checkRequestParam() {
        return !(budgetTypeEconom && budgetTypeStandard) &&
                !(budgetTypeLux && budgetTypeStandard) &&
                !(budgetTypeLux && budgetTypeEconom);
    }
}

package com.yaroslav.hotel.entity;

/**
 * Created by employee on 6/8/15.
 */
public class HqlQueryHotelRoomSearchBuilder {

    private static String BUDGET_SQL_TOKEN = "HR.classRoom %1s :classRoom  and ";

    private Period period;

    private Integer countHotelRoom;

    private SizeRoomType sizeRoomType = null;

    private BudgetRoomType budgetRoomType = null;

    private Boolean thisBudgetRoomType = true;

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Integer getCountHotelRoom() {
        return countHotelRoom;
    }

    public void setResultCountHotelRoom(Integer countHotelRoom) {
        this.countHotelRoom = countHotelRoom;
    }

    public SizeRoomType getSizeRoomType() {
        return sizeRoomType;
    }

    public void setSizeRoomType(SizeRoomType sizeRoomType) {
        this.sizeRoomType = sizeRoomType;
    }

    public BudgetRoomType getBudgetRoomType() {
        return budgetRoomType;
    }

    public void setBudgetRoomType(BudgetRoomType budgetRoomType) {
        this.budgetRoomType = budgetRoomType;
    }

    public Boolean getThisBudgetRoomType() {
        return thisBudgetRoomType;
    }

    public void setThisBudgetRoomType(Boolean thisBudgetRoomType) {
        this.thisBudgetRoomType = thisBudgetRoomType;
    }

    public void setNotThisBudgetRoomType(BudgetRoomType budgetRoomType) {
        this.budgetRoomType = budgetRoomType;
        this.thisBudgetRoomType = false;
    }

    public long getStartTime() {
        return period.begin.getTime();
    }

    public long getEndTime() {
        return period.end.getTime();
    }

    public String generateQuery() {
        String result = "";

        result += "select HR from HotelRoom HR where ";
        result += getGeneratedBudgetRoomQuery();
        result += getGeneratedSizeRoomQuery();
        result += "( not exists " +
                "(from Reservation R where R.hotelRoom = HR and " +
                "(R.startDate between :startDate and :endDate or " +
                "R.endDate between :startDate and :endDate)))";

        return result;
    }

    private String getGeneratedBudgetRoomQuery() {
        if (budgetRoomType != null) {
            String budgetSQLComparisonCondition = thisBudgetRoomType ? "=" : "<>";
            return String.format(BUDGET_SQL_TOKEN, budgetSQLComparisonCondition);
        }

        return "";
    }

    private String getGeneratedSizeRoomQuery() {
        if (sizeRoomType != null) {
            return  "HR.type = :sizeRoom and ";
        }

        return "";
    }
}

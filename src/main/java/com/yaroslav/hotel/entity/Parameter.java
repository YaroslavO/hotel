package com.yaroslav.hotel.entity;

/**
 * Created by employee on 6/8/15.
 */
public class Parameter {
    public Period period;
    public Integer countHotelRoom;
    public SizeRoomType sizeRoomType = null;
    public BudgetRoomType budgetRoomType = null;
    public Boolean thisBudgetRoomType = true;

    public void setNotThisBudgetRoomType(BudgetRoomType budgetRoomType) {
        this.budgetRoomType = budgetRoomType;
        this.thisBudgetRoomType = false;
    }
}

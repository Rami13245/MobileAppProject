package com.example.testingg;

public class TrendItem {
    private String date;
    private int totalCalories;

    public TrendItem(String date, int totalCalories) {
        this.date = date;
        this.totalCalories = totalCalories;
    }

    public String getDate() {
        return date;
    }

    public int getTotalCalories() {
        return totalCalories;
    }
}

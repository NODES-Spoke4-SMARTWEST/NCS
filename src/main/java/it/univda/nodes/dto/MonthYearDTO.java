package it.univda.nodes.dto;

public class MonthYearDTO {
    private String monthName;
    private int monthValue;
    private int year;

    public MonthYearDTO(String monthName, int monthValue, int year) {
        this.monthName = monthName;
        this.monthValue = monthValue;
        this.year = year;
    }

    // Getters
    public String getMonthName() { return monthName; }
    public int getMonthValue() { return monthValue; }
    public int getYear() { return year; }
}

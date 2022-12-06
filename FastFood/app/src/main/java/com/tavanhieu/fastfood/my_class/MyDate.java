package com.tavanhieu.fastfood.my_class;

import java.io.Serializable;

public class MyDate implements Serializable {
    private int day, month, year, hours, minutes, second;

    public MyDate() {
    }

    public MyDate(int day, int month, int year, int hours, int minutes, int second) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.minutes = minutes;
        this.second = second;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public String getDayMonthYear() {
        return day + "" + month + "" + year;
    }

    public String getHourMinute() {
        return hours + "" + minutes;
    }
}

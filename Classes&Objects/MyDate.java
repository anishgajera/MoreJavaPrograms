package com.company;
import java.util.GregorianCalendar;

/*
    Class "MyDate" -
    This class is defined in Programming Exercise 10.14.
    It will be used to create an object for date hired
    in the Employee class which is a subclass of the
    Person class created.

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */

public class MyDate
{
    //initial data fields
    private int year;
    private int month;
    private int day;

    //create a MyDate object
    MyDate()
    {
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(GregorianCalendar.YEAR);
        month = calendar.get(GregorianCalendar.MONTH);
        day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    //MyDate object with elapsed time
    MyDate(long elapsedTime)
    {
        setDate(elapsedTime);
    }

    //create MyDate object with specified values
    MyDate(int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    //accessor for year
    public int getYear()
    {
        return year;
    }
    //accessor for month
    public String getMonth()
    {
        String m = String.valueOf(month + 1);
        return (month < 10 ? "0" + m : m);
    }
    //accessor for day
    public String getDay()
    {
        String d = String.valueOf(day);
        //enhanced
        return (day < 10 ? "0" + d : d);
    }

    //mutator for date
    public void setDate(long elapsedTime)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);
        year = calendar.get(GregorianCalendar.YEAR);
        month = calendar.get(GregorianCalendar.MONTH);
        day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

}

package com.company;
import java.util.*;
import java.util.Scanner;

/*
    HW2 - Days of a month
    This program asks the user to enter
    the year and the month they wish,
    and returns how many days were in that
    month in the year they specified.

    Written by Anish Gajera starting on Tuesday, February 15, 2022, for CS 2336.501
 */

public class DaysOfMonth
{
    public static void main(String[] args)
    {
        //variables to store the year and the month as entered by the user
        int year;
        String month;
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt user to enter necessary input
        System.out.print("Enter a year: ");
        year = scan.nextInt();
        System.out.print("Enter a month: ");
        month = scan.next();
        //function getMonthDays outputs the number of days in the month of the year (as entered by user)
        getMonthDays(month, year);
    }

    public static void getMonthDays(String month, int year)
    {
        //variable to hold the number of days in the month user entered
        int daysInMonth;
        //if the month is any of these, then it should have 30 days
        if (month.equals("Apr") || month.equals("Jun") || month.equals("Sep") || month.equals("Nov"))
        {
            daysInMonth = 30;
            System.out.println(month + " " + year + " has " + daysInMonth + " days");
        }
        //if the month is February, check if its a leap year month
        else if (month.equals("Feb"))
            {
                //enhanced for loop to check if its a leap year (if it is, then 29 days, else, 28 days)
                daysInMonth = (year % 4 == 0) ? 29 : 28;
                System.out.println(month + " " + year + " has " + daysInMonth + " days");
            }
            //else, the days in the month should be 31 days
            else
            {
                daysInMonth = 31;
                System.out.println(month + " " + year + " has " + daysInMonth + " days");
            }
        }
}

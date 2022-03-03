package com.company;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;

/*
    HW3 - Statistics: compute deviation
    This program takes in 10 values from the
    user, stores them in an array, and computes
    the mean and standard deviation of them.

    Written by Anish Gajera starting on Monday, February 21, 2022, for CS 2336.501
 */

public class statistics
{
    public static void main(String[] args)
    {
        //array to hold the 10 values entered by the user
        double[] array = new double[10];
        //variable to hold each value that will be loaded into the array
        double value;
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt the user to enter in 10 values
        System.out.print("Enter ten values: ");
        //for loop to take in each of the 10 values and load it into the array
        for (int i = 0; i < array.length; i++)
        {
            value = scan.nextDouble();
            array[i] = value;
        }

        //print the mean with proper decimal spaces (per assignment instructions)
        System.out.print("The mean is: ");
        System.out.printf("%.2f", mean(array));
        //for proper spacing and new line(s) (per assignment instructions)
        System.out.print("\n");
        //print the standard deviation with proper decimal spaces (per assignment instructions)
        System.out.print("The standard deviation is: ");
        System.out.printf("%.5f", deviation(array));
        System.out.print("\n");

    }

    public static double mean(double[] x)
    {
        //variable to hold the total after adding all 10 elements in the array
        double total = 0;
        //variable which holds the mean
        double mean;
        //for each value in the array (10 of them), add them all up and store in total
        for (int i = 0; i < x.length; i++)
        {
            total += x[i];
        }
        //calculate the mean (total / 10)
        mean = total / x.length;

        //return the mean
        return mean;
    }

    public static double deviation(double[] x)
    {
        //get the mean which will be used in the deviation calculation
        double mean = mean(x);
        //variable to hold the deviation
        double deviation = 0;
        //for loop to go through and calculate the summation portion for the deviation formula and square the value
        for (int i = 0; i < x.length; i++)
        {
            //the deviation before square root
            deviation += Math.pow(x[i] - mean, 2);
        }

        //return the standard deviation which is the square root of what is in the formula (per assignment information)
        return Math.sqrt(deviation / (x.length - 1));
    }


}

/* Notes: (Ignore)
double total = 0;
    for (int i = 0; i < x.length; i++)
    {
        total += x[i];
    }
    double mean = total / x.length;
    //calculate the standard deviation
    for(int i = 0; i < x.length; i++)
    {
        total += Math.pow((x[i] - mean),2);
    }
    mean = total / (x.length-1);
    double stDeviation = Math.sqrt(mean);

    //call he function to print the deviation
    printDeviation(stDeviation);
    //return the standard deviation
    return stDeviation;



     public static void printMean(double mean)
    {
        System.out.print("The mean is: ");
        System.out.printf("%.2f", mean);
        System.out.print("\n");
    }

    public static void printDeviation(double stDeviation)
    {
        System.out.print("The standard deviation is: ");
        System.out.printf("%.5f", stDeviation);
        System.out.print("\n");
    }

 */
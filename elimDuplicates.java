package com.company;
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;

/*
    HW3 - Eliminate duplicates
    This program takes in ten values and
    performs a linear search to find and
    eliminate duplicate values and returns
    the new values without duplicates.

    Written by Anish Gajera starting on Monday, February 21, 2022, for CS 2336.501
 */


public class elimDuplicates
{
    public static void main(String[] args)
    {
        //variable that will hold the value(s) the user will enter (these will be stored in the array)
        int value;
        //array that will hold the initial 10 values the user enters
        int[] array = new int[10];
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt the user to enter 10 values
        System.out.print("Enter ten values: ");
        //for loop to take in each of the 10 values and load it into the array
        for (int i = 0; i < 10; i++)
        {
            value = scan.nextInt();
            array[i] = value;
        }
        //call function which will eliminate duplicates, set the new array to the function call
        int[] newArray = eliminateDuplicates(array);
        //output the new array without duplicates
        System.out.print("The distinct numbers are: ");
        for(int val: newArray)
        {
            if(val > 0)
            {
                System.out.print(" " + val);
            }
        }
        System.out.print("\n");


    }

    public static int[] eliminateDuplicates(int[] list)
    {
        //create an array to hold the new values (without duplicates)
        int[] newList = new int[list.length];
        int i = 0; //for the i value in for loop (using an enhanced for loop)
        //for a value (val) in list
        for (int val: list)
        {
            //call the linear search function to go through and search the array for duplicate values
            //if the return value is -1
            if(linSearch(newList, val) == -1)
            {
                newList[i] = val;
                i++;
            }
        }

        //return the array newList as it is the one without the duplicates
        return newList;
    }

    //function which performs a linear search on the initial array
    public static int linSearch(int[] array, int key)
    {
        //use for loop to parse array and use linear search to look for repeating values
        for (int i =0; i < array.length; i++)
        {
            //if the key value is the same as array[i]
            if (key == array[i])
            {
                //then return 1 (i.e. true)
                return 1;
            }
        }
        //else, return -1 (i.e. false)
        return -1;
    }
}

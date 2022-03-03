package com.company;
import java.util.*;
import java.util.Scanner;

/*
    HW3 - Sorted
    This program takes in a list of values
    (size determined by user) and checks
    whether they are sorted or not. If not, then it
    returns false, otherwise, true.

    Written by Anish Gajera starting on Monday, February 21, 2022, for CS 2336.501
 */

public class sorted
{
    public static void main(String[] args)
    {
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt the user to enter list (the first value they enter will be the list length)
        System.out.print("Enter list: ");
        //array to store the 10 values the user enters
        int[] array = new int[scan.nextInt()];
        //for loop to store the rest of the values after the initial value (which is the list length)
        for (int i = 0; i < array.length; i++)
        {
            //set each value of the list to the values entered by the user
            array[i] = scan.nextInt();
        }

        //output the final result (whether the list is sorted or not)
        //enhanced method to output (one-line)
        System.out.println("The list is " + (isSorted(array) ? "already " : "not ") + "sorted");

    }

    //function to check if the list is sorted or not
    public static boolean isSorted(int[] list)
    {
        //for loop to iterate through the list and check each value until either true at end or false somewhere in the middle
        for(int i = 0; i < list.length - 1; i++)
        {
            //if the value at the next index is less than the value at the previous, the list is not sorted
            if (list[i] > list[i + 1])
            {
                //since list is not sorted, return false
                return false;
            }
        }
        //else return true if the list is sorted
        return true;
    }
}

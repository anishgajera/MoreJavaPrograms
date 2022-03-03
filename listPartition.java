package com.company;
import java.util.*;
import java.util.Scanner;

/*
    HW3 - Partition of a list
    This program takes in a list of values from
    the user and uses a pivot to partition the list.
    The lower values remain prior to the pivot,
    and the higher values go past the pivot.

    Written by Anish Gajera starting on Monday, February 21, 2022, for CS 2336.501
 */

public class listPartition
{
    public static void main(String[] args)
    {
        //variable which holds the size of the array (first value entered by the user)
        int size;
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt the user to enter the list
        System.out.print("Enter list: ");
        //get the size from the user
        size = scan.nextInt();
        //array to hold the values entered by the user
        int[] array = new int[size];
        //for loop to assign the values to the array
        for (int i = 0; i < size; i++)
        {
            array[i] = scan.nextInt();
        }

        //call the partition function on the array
        partition(array);
    }

    public static int partition(int[] list)
    {
        //8 10 1 5 16 61 9 11 1
        //value of first index
        int first = 0;
        //value of lower index
        int low = first + 1;
        //value of higher index
        int high = list.length - 1;
        //the pivot in the partition method
        int pivot = list[first];

        //while the high is actually higher than the low value (as it should be)
        while (high > low) {
            //while the low is <= high and the value at the low index is <= the pivot value
            while (low <= high && list[low] <= pivot) {
                //increment the low
                low++;
            }
            //while the low is <= high and the value at the high index is > the pivot value
            while (low <= high && list[high] > pivot) {
                //decrement the high
                high--;
            }
            //if high is greater than low after this, then
            if (high > low) {
                //set the temp variable to the value of the array at index "high"
                int temp = list[high];
                //set the value of the array at index high to the value of the array at index low
                list[high] = list[low];
                //set the value of the array at the low to the temp which is list[high]
                list[low] = temp;
            }
        }
            //while high is >= low and the list at high is >= the pivot
            while (high >= low && list[high] >= pivot)
            {
                //decrement the high
                high--;
            }
            //if higher index is greater than first index (which it should be)
            if (high > first)
            {
                //set the temp variable to the value of the array at index "high"
                int temp = list[high];
                //set the value of the array at index high to the value of the array at first index
                list[high] = list[first];
                //set the value of the array at the first index to the temp which is list[high]
                list[first] = temp;
                System.out.print("After the partition: ");
                for (int i = 0; i < list.length; i++)
                {
                    System.out.print(list[i] + " ");
                }
                return high;
            }
            //else, return the first index
            else {

                System.out.print("After the partition: ");
                for (int i = 0; i < list.length; i++)
                {
                    System.out.print(list[i] + " ");
                }
                return first;
            }
        }

    }

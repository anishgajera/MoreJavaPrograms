package com.company;
import java.util.*;
import java.util.Scanner;

/*
    HW3 - CCN Validation
    This program takes in credit card
    numbers and uses the Luhn check
    (Mod 10 check) to see if the ccn entered
    is valid or not.

    Written by Anish Gajera starting on Monday, February 21, 2022, for CS 2336.501
 */

public class ccnValidation
{
    public static void main(String[] args)
    {
        //variable which will hold the ccn
        long ccn;
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt user to enter ccn as a long number
        System.out.print("Enter credit card number as a long number: ");
        //obtain and assign the value entered to variable
        ccn = scan.nextLong();

        //output whether ccn entered is valid or not
        System.out.println(ccn + " is " + (isValid(ccn) ? "valid " : "invalid"));

    }

    public static boolean isValid(long number)
    {
        //perform check to see if the ccn is valid, if it is then the proper return value will be returned from each of the below functions
        //return will be outputted in main
        boolean isValid = (getSize(number) >= 13 && getSize(number) <= 16) && (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 37) || prefixMatched(number, 6)) && ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);

        return isValid;
    }

    //step 2 of Luhn check (per assignment instructions)
    public static int sumOfDoubleEvenPlace(long number)
    {
        //variable to hold the sum
        int sum = 0;
        //string variable ccn to hold the number entered by the user
        String ccn = number + "";
        for (int i = getSize(number) - 2; i >= 0; i-= 2)
        {
            sum += getDigit(Integer.parseInt(ccn.charAt(i) + "") * 2);
        }

        return sum;
    }

    //function to get digit and return single digit if single digit is entered, else return sum of 2 digits (based on Luhn check, per assignment instructions)
    public static int getDigit(int number)
    {
        //if number is less than 9 (value based)
        if (number < 9)
        {
            //return number
            return number;
        }
        //else perform Luhn check operation(s) and return
        else
        {
            return number / 10 + number % 10;
        }
    }

    //function which adds the numbers in the odd indexes of the ccn (based on Luhn Check, per assignment instructions)
    public static int sumOfOddPlace(long number)
    {
        //variable to hold the sum
        int sum = 0;
        //string variable ccn to hold the number entered by the user
        String ccn = number + "";
        for (int i = getSize(number) - 1; i >= 0; i-= 2)
        {
            sum += Integer.parseInt(ccn.charAt(i) + "");
        }

        return sum;
    }

    //function to check if the prefix of the number entered is a valid card (i.e. Amex, Visa, MasterCard, Discover, per assignment)
    public static boolean prefixMatched(long number, int d)
    {
        return getPrefix(number, getSize(d)) == d;
    }

    //this function returns the size of the ccn entered (i.e. the number of digits in the ccn entered)
    public static int getSize(long d)
    {
        //variable ccn to get the number of digits
        String ccn = d + "";

        //used inline variable (intellij recommendation)
        return ccn.length();
    }

    public static long getPrefix(long number, int k)
    {
        //if the ccn entered has more than k digits, then
        if (getSize(number) > k)
        {
            //string variable ccn to hold the number entered by the user
            String ccn = number + "";
            //return first k digits from number
            return Long.parseLong(ccn.substring(0, k));
        }
        //if not greater than k digits
        else
        {
            //then just return the number
            return number;
        }
    }
}

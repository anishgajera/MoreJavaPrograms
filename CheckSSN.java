package com.company;
import java.util.regex.*;
import java.util.Scanner;

/*
    HW2 - Check SSN
    This program accepts an SSN from the user
    and then checks to see if it is valid or
    not.

    Written by Anish Gajera starting on Tuesday, February 15, 2022, for CS 2336.501
 */

public class CheckSSN
{
    public static void main(String[] args)
    {
        //variable that will hold the ssn entered by the user
        String ssn;
        //scanner object
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter an SSN: ");
        ssn = scan.nextLine();
        //function validateSSN is used to check if the SSN is valid or not
        validateSSN(ssn);
    }

     public static void validateSSN(String s)
    {
        //regex string to check if ssn entered is valid (this is because certain values are not in SSN numbers, so this checks for those certain values)
        String regex = "^(?!666|000|9\\d{2})\\d{3}" + "-(?!00)\\d{2}-" +"(?!0{4})\\d{4}$";
        //compile the regex string via pattern
        Pattern p = Pattern.compile(regex);
        //use matcher (from pattern class) to see if there is anything from in between the regex string that matches what is entered
        Matcher m = p.matcher(s);
        //if the ssn entered is invalid and matches anything from the regex string
        if(!(m.matches()))
        {
            System.out.println(s + " is an invalid social security number");
        }
        //else if the ssn entered is valid and does not match anything from the regex string
        else if (m.matches())
        {
            System.out.println(s + " is a valid social security number");
        }
    }
}
